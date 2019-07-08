package com.nasable.magiclibs.MagicLibs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class NumberUtility {



    public static Locale getLocalByLangCode(String language,String country){
        return new Locale(language, country);
    }

    public static  CountryCurrency getDefaultCurrency(){
        Locale defaultLocale = Locale.getDefault();
        Currency currencies = Currency.getInstance(defaultLocale);
        return new CountryCurrency(defaultLocale.getDisplayCountry(), defaultLocale.getCountry(),defaultLocale.getLanguage(), currencies.getCurrencyCode(), currencies.getSymbol());
    }


    public static List<CountryCurrency> getAvailableCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        List<CountryCurrency> countries = new ArrayList<CountryCurrency>();
        for (Locale locale : locales) {
           try {
               Currency currencies = Currency.getInstance(locale);
               CountryCurrency countryCurrency=new CountryCurrency(locale.getDisplayCountry(), locale.getCountry(),locale.getLanguage(), currencies.getCurrencyCode(), currencies.getSymbol());
               if(!countries.contains(countryCurrency))
                 countries.add(countryCurrency);

           }catch (Exception e){}
        }
        Collections.sort(countries, new Comparator<CountryCurrency>() {
            @Override
            public int compare(final CountryCurrency object1, final CountryCurrency object2) {
                return object1.getCountry().compareTo(object2.getCountry());
            }
        });
        return countries;
    }

    public static class CountryCurrency {
        private String country;
        private String countryCode;
        private String countryLanguage;

        private String currencyCode;
        private String currencySymbol;

        public CountryCurrency(String country, String countryCode, String countryLanguage, String currencyCode, String currencySymbol) {
            this.country = country;
            this.countryCode = countryCode;
            this.countryLanguage = countryLanguage;
            this.currencyCode = currencyCode;
            this.currencySymbol = currencySymbol;
        }

        @Override
        public boolean equals( Object obj) {
            return obj instanceof CountryCurrency &&
                    ((CountryCurrency)obj).country.equals(country)&&
                    ((CountryCurrency)obj).countryCode.equals(countryCode)&&
                    ((CountryCurrency)obj).currencyCode.equals(currencyCode)&&
                    ((CountryCurrency)obj).currencySymbol.equals(currencySymbol);
        }

        public String getCountry() {
            return country;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getCountryLanguage() {
            return countryLanguage;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }
    }

}
