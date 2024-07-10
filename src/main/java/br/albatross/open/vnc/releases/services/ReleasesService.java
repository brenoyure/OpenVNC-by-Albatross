package br.albatross.open.vnc.releases.services;

import br.albatross.open.vnc.releases.model.Release;

import java.util.Optional;

public interface ReleasesService {

    Optional<Release> getTheLatestStable();

}
