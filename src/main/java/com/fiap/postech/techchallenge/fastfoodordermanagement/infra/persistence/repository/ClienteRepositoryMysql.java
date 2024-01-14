package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepositoryMysql  extends JpaRepository<ClienteEntity, String> {

    Optional<ClienteEntity> findByCpf(String cpf);


}
