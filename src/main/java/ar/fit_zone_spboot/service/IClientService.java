package ar.fit_zone_spboot.service;

import ar.fit_zone_spboot.model.Client;

import java.util.List;

public interface IClientService {
    public List<Client> listClients();
    public Client findClientById(Integer idClient);
    public void saveClient(Client client);
    public void deleteClient(Integer idClient);
}
