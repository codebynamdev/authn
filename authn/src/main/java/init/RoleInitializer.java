package init;

import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.RoleRepository;

@Component
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    public RoleInitializer(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotExists("USER");
        createRoleIfNotExists("DRIVER");
        createRoleIfNotExists("ADMIN");
    }
    private void createRoleIfNotExists(String name) {
        if(!roleRepository.existsByName(name)) {
            Role role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }
}
