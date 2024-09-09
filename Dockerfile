# Usar una imagen base de WildFly
FROM jboss/wildfly:latest

# Copiar el archivo EAR al contenedor
COPY deploy/ear/*.ear /opt/jboss/wildfly/standalone/deployments/

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para iniciar WildFly
CMD ["jboss-cli.sh", "--connect", "--file=/opt/jboss/wildfly/standalone/configuration/standalone.xml"]
