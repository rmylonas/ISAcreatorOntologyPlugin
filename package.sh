mvn clean install

cd target/
mkdir ISAcreatorOntologyPlugin/
mkdir ISAcreatorOntologyPlugin/config
cp -r ../config/ ISAcreatorOntologyPlugin/config/
mv ISAcreatorOntologyPlugin-0.1.jar ISAcreatorOntologyPlugin/
