Custom Installation in Local Repository
=======================================

To install this jar after compilation in a custom repository without having
to change the `pom.xml` file use the maven `deploy:deploy-file` functionality.
This assumes that ssh is available in the path (installing msys2 and running
maven from the msys2 command line is sufficient). This method uses
passwordless ssh which is out of scope please see references for more detail.

Update `settings.xml` to include an entry for your custom repository, see
below for a complete `settings.xml`:

    <settings>
        <server>
            <id>ssh-repository-nasbox</id>
            <privateKey>C:\Users\User\.ssh\id_rsa</privateKey>
        </server>
    </settings>

Then run Maven with the `deploy:deploy-file` target and many options:

    mvn deploy:deploy-file -DgroupId=uk.e42 -DartifactId=downsample \
        -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar \
        -DrepositoryId=ssh-repository-nasbox \
        -Durl=scpexe://ben@192.168.1.6/home/ben/public_html/maven/3.0/repo \
        -Dfile=target/downsample-0.0.1-SNAPSHOT.jar

The pom you are using will need the apache wagon ssh extension, the `pom.xml`
in this repository includes the extension.

It is possible to use transports other than ssh, please see the references.

`settings.xml`
--------------

This file is located the `.m2` directory which is in your home directory (on
Windows this is: `C:\Users\<username\.m2`). A complete `settings.xml` file is
provided below for reference. I recommend looking at the maven website to
understand in more detail (see references).

    <settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
        <server>
            <id>ssh-repository-nasbox</id>
            <privateKey>C:\Users\User\.ssh\id_rsa</privateKey>
        </server>
    </settings>

References
----------

* [passwordless ssh](https://www.hostinger.com/tutorials/how-to-setup-passwordless-ssh/) hostinger tutorial by Edward S.
* [maven `settings.xml` reference](https://maven.apache.org/settings.html) from the source
* [maven deploy:deploy-file mojo](https://maven.apache.org/plugins/maven-deploy-plugin/deploy-file-mojo.html)

