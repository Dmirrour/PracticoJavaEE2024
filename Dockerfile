# Usa la imagen base de WildFly
FROM jboss/wildfly:latest

# Copia el archivo .ear al contenedor en el directorio de despliegue de WildFly
COPY ear/target/PracticoJavaEE2024.ear /opt/jboss/wildfly/standalone/deployments/

# Expone los puertos en los que WildFly escucha
EXPOSE 8080 9990

# Comando para iniciar WildFly
CMD ["jboss-cli.sh", "--connect", "--file=/opt/jboss/wildfly/standalone/configuration/standalone.xml"]