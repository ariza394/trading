package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.ServicioTomado;
import com.carlos.usuarios.beusers.repositories.ServicioTomadoRepository;

@Service
public class ServicioTomadoServiceImpl implements ServicioTomadoService{

    @Autowired
    private ServicioTomadoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ServicioTomado> findAll() {
        return (List<ServicioTomado>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioTomado> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ServicioTomado save(ServicioTomado servicioTomado) {
        return repository.save(servicioTomado);
    }

    @Override
    @Transactional
    public Optional<ServicioTomado> update(ServicioTomado servicioTomado, Long id) {
        Optional<ServicioTomado> o = this.findById(id);
        ServicioTomado servicioOptional = null;
        if(o.isPresent()){
            ServicioTomado servicioDb = o.orElseThrow();
            servicioDb.setUsuario(servicioTomado.getUsuario());
            servicioDb.setServicio(servicioTomado.getServicio());
            servicioDb.setFin(servicioTomado.getFin());
            servicioOptional = this.save(servicioDb);
        }

        return Optional.ofNullable(servicioOptional);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
}
