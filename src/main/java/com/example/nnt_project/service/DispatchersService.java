package com.example.nnt_project.service;

import com.example.nnt_project.entity.Dispatchers;
import com.example.nnt_project.repository.DispatchersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispatchersService {

    private final DispatchersRepository dispatchersRepository;

    public Optional<Dispatchers> getDispatcherById(UUID id) {
        return dispatchersRepository.findById(id);
    }

    public Dispatchers saveDispatcher(Dispatchers dispatcher) {
        return dispatchersRepository.save(dispatcher);
    }

    public void deleteDispatcher(UUID id) {
        dispatchersRepository.deleteById(id);
    }

    public Optional<Dispatchers> updateDispatcher(UUID id, Dispatchers updatedDispatcher) {
        return dispatchersRepository.findById(id).map(existingDispatcher -> {
            existingDispatcher.setNumberOfLoads(updatedDispatcher.getNumberOfLoads());
            existingDispatcher.setGrossRevenue(updatedDispatcher.getGrossRevenue());
            existingDispatcher.setNetProfit(updatedDispatcher.getNetProfit());
            existingDispatcher.setOpenLoads(updatedDispatcher.getOpenLoads());
            existingDispatcher.setUser(updatedDispatcher.getUser());
            return dispatchersRepository.save(existingDispatcher);
        });
    }

    public List<Dispatchers> getAllDispatchers() {
        return dispatchersRepository.findAll();
    }

    public List<Dispatchers> getDispatchersByDateRange(String startDate, String endDate) {
        // Implement the logic to filter dispatchers by date range
        return dispatchersRepository.findAll(); // Placeholder, implement your date filtering logic here
    }
}
