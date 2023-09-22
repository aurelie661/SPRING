package com.example.demo_path_request.services;
import com.example.demo_path_request.models.PersonDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
public class PersonService {
    private final Map<UUID, PersonDTO> persons;

    public PersonService() {
        persons = new HashMap<>();

        PersonDTO personA = PersonDTO.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("DUPONT")
                .age(47)
                .build();

        PersonDTO personB = PersonDTO.builder()
                .id(UUID.randomUUID())
                .firstName("Maria")
                .lastName("DUPONT")
                .age(39)
                .build();

        PersonDTO personC = PersonDTO.builder()
                .id(UUID.randomUUID())
                .firstName("Chloée")
                .lastName("SMITH")
                .age(7)
                .build();

        persons.put(personA.getId(), personA);
        persons.put(personB.getId(), personB);
        persons.put(personC.getId(), personC);
    }

    public List<PersonDTO> getPersons() {
        return persons.values().stream().toList();
    }

    public Optional<PersonDTO> getPersonById(UUID id) {
        return persons.values()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
    public void addPerson(PersonDTO newPerson) {
        if(newPerson.getId() == null){
            newPerson.setId(UUID.randomUUID());
        }
        persons.put(newPerson.getId(),newPerson);

    }

    public boolean deletePerson(UUID id) {
        Optional<PersonDTO> personFound = getPersonById(id);

        if(personFound.isPresent()){
            personFound.ifPresent(personDTO -> persons.remove(personDTO.getId()));
            return true;
        }
        return false;
    }

    public PersonDTO editPerson(UUID id, PersonDTO personDTO){
        AtomicReference<PersonDTO> personDTOAtomicReference = new AtomicReference<>();

        Optional<PersonDTO> personDTOOptional = getPersonById(id);

        personDTOOptional.ifPresentOrElse(found ->{
            if(personDTO.getFirstName() != null){
                found.setFirstName(personDTO.getFirstName());
            }
            if(personDTO.getLastName() != null){
                found.setLastName(personDTO.getLastName());
            }
            if(personDTO.getAge() != null){
                found.setAge(personDTO.getAge());
            }
            personDTOAtomicReference.set(found);
        }, () -> {
            personDTOAtomicReference.set(null);
        });
    return personDTOAtomicReference.get();
    }
}
