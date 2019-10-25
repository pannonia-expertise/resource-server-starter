# resource-server starter

**How to use**<br />
-Build JAR for this project <br />
-Import built JAR as maven dependency to target project <br />
-Add following annotations to your adequate configuration class <br />
```java
@EnableJpaRepositories(basePackages = {"com.pannoniaexpertise.PROJECT_PACKAGE_HERE*", "com.pannoniaexpertise.resourceserver.*"})
@EntityScan(basePackages = {"com.pannoniaexpertise.PROJECT_PACKAGE_HERE*", "com.pannoniaexpertise.resourceserver.*"})
```
<br />
-Congratulations, resource servcer is now ready to use <br />
-To limit permissions add annotation *@PreAuthorize("hasPermission('RESOURCE', 'OPERATION')")* before controller methods. <br />

<br />

**Contributors**
<br />
<ul>
<li>Nikola Stanar @nstanar</li>
<li>Petar Ostojic @master312</li>
<li>Nikola Antic</li>
<li>Milena Lalic</li>
<li>Nina Damjanovic</li>
<li>Nebojsa Krtolica @NebojsaKrtolica</li>
<li>Stevan Hartig @olesteva</li>
</ul>


