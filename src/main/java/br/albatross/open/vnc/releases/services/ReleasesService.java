package br.albatross.open.vnc.releases.services;

import java.util.List;
import java.util.Optional;

import br.albatross.open.vnc.releases.model.Release;

public interface ReleasesService {

    Optional<Release> getTheLatestStable();
    Optional<Release> getTheLastest();
    List<Release> findAll();

    boolean isTheCurrentReleaseUpdated();

}
