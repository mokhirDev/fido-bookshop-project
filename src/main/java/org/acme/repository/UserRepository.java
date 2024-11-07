package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.base.BaseRepository;
import org.acme.entity.User;

@ApplicationScoped
public class UserRepository implements BaseRepository<User, Long> {
}
