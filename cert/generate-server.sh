echo "######Making dir"
mkdir tmp-cert
echo "###### GENERATE CERTIF"
keytool -genkey -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -keystore tmp-cert/serverkeystore.p12 \
-ext san=ip:127.0.0.1,dns:localhost \
-storepass password \
-alias serverkey \
-validity 365 \
-dname "cn=me, ou=unit, o=org, c=MA"

echo ""
echo "###### EXPORT CERTIF"
keytool -exportcert -keystore tmp-cert/serverkeystore.p12 -alias serverkey -storepass password -rfc -file tmp-cert/server-certificate.pem

echo ""
echo "###### IMPORT CERTIF"
keytool -import -noprompt -trustcacerts -file tmp-cert/server-certificate.pem -keypass password -storepass password -keystore tmp-cert/clienttruststore.jks