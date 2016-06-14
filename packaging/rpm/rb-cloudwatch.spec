Name:    rb-cloudwatch
Version: %{__version}
Release: %{__release}%{?dist}

License: GNU AGPLv3
URL: https://github.com/redBorder/rb-cloudwatch
Source0: %{name}-%{version}.tar.gz

BuildRequires: maven java-devel

Summary: Get monitor events from kafka and send them to aws cloudwatch service. 
Group:   Services/Monitoring
Requires: java

%description
%{summary}

%prep
%setup -qn %{name}-%{version}

%build
mvn clean package

%install
mkdir -p %{buildroot}/usr/share/%{name}
mkdir -p %{buildroot}/etc/%{name}
install -D -m 644 rb-cloudwatch.service %{buildroot}/usr/lib/systemd/system/rb-cloudwatch.service
install -D -m 644 src/main/resources/config.json %{buildroot}/etc/%{name}/config.json
install -D -m 644 target/rb_cloudwatch-*-selfcontained.jar %{buildroot}/usr/share/%{name}

%clean
rm -rf %{buildroot}

%pre
getent group %{name} >/dev/null || groupadd -r %{name}
getent passwd %{name} >/dev/null || \
    useradd -r -g %{name} -d / -s /sbin/nologin \
    -c "User of %{name} service" %{name}
exit 0

%post -p /sbin/ldconfig
%postun -p /sbin/ldconfig

%files
%defattr(644,root,root)
/usr/share/%{name}
%defattr(644,root,root)
/etc/%{name}/config.json
/usr/lib/systemd/system/rb-cloudwatch.service

%changelog
* Fri Jun 10 2016 Alberto Rodriguez <arodriguez@redborder.com> - 1.0.19-1
- first spec version
