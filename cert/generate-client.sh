echo "######Making dir"
mkdir tmp-cert
echo "###### GENERATE CERTIF"
keytool -genkey -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -keystore tmp-cert/clientkeystore.p12  \
-ext san=ip:127.0.0.1,dns:localhost \
-storepass password \
-alias clientkey \
-validity 999999 \
-dname "cn=me, ou=unit, o=org, c=MA"

echo ""
echo "###### EXPORT CERTIF"
keytool -exportcert -keystore tmp-cert/clientkeystore.p12 -alias clientkey -storepass password -rfc -file tmp-cert/client-certificate.pem

echo ""
echo "###### IMPORT CERTIF"
keytool -import -noprompt -trustcacerts -file tmp-cert/client-certificate.pem -keypass password -storepass password -keystore tmp-cert/servertruststore.jks