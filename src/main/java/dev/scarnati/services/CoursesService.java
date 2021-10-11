package dev.scarnati.services;

import dev.scarnati.repositories.CourseRepo;

public class CoursesService {
    CourseRepo courseRepo = new CourseRepo();
    public Float getCostById(Integer id)  {
        float cost = courseRepo.getById(id);
        return cost;

    }
}
