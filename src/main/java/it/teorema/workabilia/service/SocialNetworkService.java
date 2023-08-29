package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.SocialNetwork;

public interface SocialNetworkService extends JpaRepository <SocialNetwork, Integer> {
}