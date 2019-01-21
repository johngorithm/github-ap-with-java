#!/bin/bash

export ANDROID_HOME="/opt/sdk/android-sdk-linux"
export PATH="${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/tools/bin:${ANDROID_HOME}/platform-tools"

cat > accept_android_licenses.sh <<EOF
#!/bin/bash
/usr/bin/expect -c'
set timeout -1;
spawn sdkmanager --licenses;
  expect {
    "y/N" { exp_send "y\r" ; exp_continue }
    eof
  }
'
EOF

sh accept_android_licenses.sh