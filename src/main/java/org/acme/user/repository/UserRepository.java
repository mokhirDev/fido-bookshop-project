package org.acme.user.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.user.aggregate.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
