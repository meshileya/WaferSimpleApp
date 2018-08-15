package com.developer.techies.wafertest.model;

public class CountryModel {

    public String countryName;
    public String countryCurrency;
    public String countryLanguage;

    public CountryModel() {

    }

    public CountryModel(String countryName, String countryCurrency, String countryLanguage) {
        this.countryName = countryName;
        this.countryCurrency = countryCurrency;
        this.countryLanguage = countryLanguage;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String title) {
        this.countryName = title;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }

    public void setCountryCurrency(String countryCurrency) {
        this.countryCurrency = countryCurrency;
    }

    public String getCountryLanguage() {
        return countryLanguage;
    }

    public void setCountryLanguage(String countryLanguage) {
        this.countryLanguage = countryLanguage;
    }
}
