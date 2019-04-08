package spring.myselfhelloworld;


import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/my_objects")
public class MyObjControler {


    private MyObjRepository myObjRepository;

    public MyObjControler(MyObjRepository myObjRepository) {

        this.myObjRepository = myObjRepository;
    }


    @GetMapping
    Iterable<MyObject> getAllObjects() {
        return myObjRepository.findAll();
    }

    @PostMapping
    public void addMyObj(@RequestBody MyObject myObject, ChildObject childObject) {

        myObjRepository.save(myObject);
    }

    @GetMapping("/{id}")
    public Optional<MyObject> getMyObj(@PathVariable Integer id) {
        return myObjRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMyObj(@PathVariable Integer id) {
        myObjRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateMyObj(@PathVariable Integer id, @RequestBody MyObject patchMyObj) {
        Optional<MyObject> optionalMyObj = myObjRepository.findById(id);
        String patchName = patchMyObj.getName();

        optionalMyObj.ifPresent(lambdaMyObj -> {
            if (!patchName.isEmpty()) {
                lambdaMyObj.setName(patchName);
            }

            Collection<ChildObject> children = lambdaMyObj.getChildObjects();
            if (children.isEmpty()) {
                lambdaMyObj.setChildObjects(children);
            }
            myObjRepository.save(lambdaMyObj);
        });
    }
}
