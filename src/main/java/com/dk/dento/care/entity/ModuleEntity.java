package com.dk.dento.care.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by khana on 31/07/16.
 */
@Entity
@Table(name = "module")
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "module_id")
    private Long id;

    @Column(name = "module_code")
    private String code;

    @Column(name = "module_description")
    private String description;

    @Column(name = "monthly_cost")
    private Long monthlyCost;

    @Column(name = "quaterly_cost")
    private Long quaterlyCost;

    @Column(name = "half_yearly_cost")
    private Long halfYearlyCost;

    @Column(name = "yearly_cost")
    private Long yearlyCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Long monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public Long getQuaterlyCost() {
        return quaterlyCost;
    }

    public void setQuaterlyCost(Long quaterlyCost) {
        this.quaterlyCost = quaterlyCost;
    }

    public Long getHalfYearlyCost() {
        return halfYearlyCost;
    }

    public void setHalfYearlyCost(Long halfYearlyCost) {
        this.halfYearlyCost = halfYearlyCost;
    }

    public Long getYearlyCost() {
        return yearlyCost;
    }

    public void setYearlyCost(Long yearlyCost) {
        this.yearlyCost = yearlyCost;
    }
}
