package com.example.basics;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
public class NumberController {

    ArrayList<AdditionAndSum> additions = new ArrayList<AdditionAndSum>();

    @GetMapping("/addition")
    public ResponseEntity<AdditionAndSum> addition(@RequestParam(name = "num1") int num1, @RequestParam(name = "num2") int num2) {

        if (num1 < 0 || num1 > 100 || num2 < 0 || num2 > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        int sum = num1 + num2;

        AdditionAndSum result = new AdditionAndSum(num1, num2, sum);

        additions.add(result);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/find")
    public ResponseEntity<ArrayList<AdditionAndSum>> find(@RequestParam(name = "num", required = false) int num, @RequestAttribute(name = "order") String order) {

        ArrayList<AdditionAndSum> filteredList = new ArrayList<>();

        if (num != null) {
            if (num < 0 || num > 100) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            filteredList = additions.stream()
                    .filter(result -> result.getAddable1() == num || result.getAddable2() == num || result.getSum() == num)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            filteredList = new ArrayList<>(additions);
        }


        if (order.equalsIgnoreCase("descending")) {
            Comparator<AdditionAndSum> comparator = Comparator.comparingInt(AdditionAndSum::getSum);
            comparator = comparator.reversed();
            filteredList.sort(comparator);
        } else if (order.equalsIgnoreCase("ascending")){
            Comparator<AdditionAndSum> comparator = Comparator.comparingInt(AdditionAndSum::getSum);
            filteredList.sort(comparator);
        }

        return ResponseEntity.ok(filteredList);
    }
}
