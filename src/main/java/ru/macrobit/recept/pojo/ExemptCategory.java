package ru.macrobit.recept.pojo;

import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.pojo.entities.Category;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 11.07.16.
 */
@Entity
public class ExemptCategory implements EntityInterface {
    @Id
    private String code;
    private String name;
    private String drugs;
    private String sovmest;

    public ExemptCategory() {

    }

    public ExemptCategory(Category category) {
        this.name = category.getDeseaseName();
        this.code = category.getCategoryCode();
        this.drugs = category.getDrugs();
        this.sovmest = category.getSovmest();
    }

    public static List<ExemptCategory> createCategories(List<Category> categories) {
        List<ExemptCategory> exemptCategories = new ArrayList<>();
        categories.stream().forEach(category -> {
            exemptCategories.add(new ExemptCategory(category));
        });
        return exemptCategories;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getSovmest() {
        return sovmest;
    }

    public void setSovmest(String sovmest) {
        this.sovmest = sovmest;
    }
}
