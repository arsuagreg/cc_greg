package com.jagdon.careconnect.sysarch.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Doctors")
public class Doctor {
    
    @Id
    private int doctorId;
    private String name;
    private String specialty;
    private String hospital;
    private String location;
    private List<String> certificates;
    private List<Education> education;
    private Contact contact;

    // Nested Education class
    public static class Education {
        private String degree;
        private String university;

        public Education() {
        }

        public Education(String degree, String university) {
            this.degree = degree;
            this.university = university;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }
    }

    // Nested Contact class
    public static class Contact {
        private String email;
        private String phone;
        private Address address;

        public Contact() {
        }

        public Contact(String email, String phone, Address address) {
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        // Nested Address class
        public static class Address {
            private String street;
            private String city;
            private String zip;
            private String country;

            public Address() {
            }

            public Address(String street, String city, String zip, String country) {
                this.street = street;
                this.city = city;
                this.zip = zip;
                this.country = country;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }
    }

    // Getters and Setters for Doctor class
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
