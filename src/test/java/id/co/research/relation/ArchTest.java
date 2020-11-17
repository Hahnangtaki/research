package id.co.research.relation;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("id.co.research.relation");

        noClasses()
            .that()
            .resideInAnyPackage("id.co.research.relation.service..")
            .or()
            .resideInAnyPackage("id.co.research.relation.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..id.co.research.relation.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
