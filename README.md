# Have I Been Pwned Connector for Identity Server

## Steps

1. Build this using `mvn clean install` command
2. Copy the `org.wso2.hibp.connector-1.0.0.jar` file to `<IS-HOME>\repository\components\dropins` folder
3. Add following configuration to `identity.xml.j2`,
`   <Resource context="(.*)/commonauth(.*)" secured="false" http-method="all"/>
`
4. 4.Restart the Identity Server.