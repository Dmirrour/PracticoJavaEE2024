# Usa la imagen base de WildFly
FROM jboss/wildfly:latest

# Copia el archivo .ear al contenedor en el directorio de despliegue de WildFly
COPY ear/target/PracticoJavaEE2024.ear /opt/jboss/wildfly/standalone/deployments/

# Expone los puertos en los que WildFly escucha
#EXPOSE 8080 9990

# Comando para iniciar WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
