# https://developer.1password.com/docs/cli/secrets-config-files/
export LDAP_ADMIN_PASS="op://Professional-IT Projects/multi-cloud-WebApp-Attendance/password"
export APP_WAR_FILE_VERSION=$(gradle getAppVersion --quiet) || true
export LDAP_HOST="localhost"
export LDAP_ADMIN="admin"
export APP_KEY=""