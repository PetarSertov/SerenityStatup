package net.jakim.utils.entities;

import java.util.Objects;

/**
 * @author yakimfb
 * @since 20.02.20
 **/
public class Employ
{
    private long id;
    private String employee_name;
    private String employee_salary;
    private int employee_age;
    private String profile_image;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return employee_name;
    }

    public void setName( String name )
    {
        this.employee_name = name;
    }

    public String getSalary()
    {
        return employee_salary;
    }

    public void setSalary( String employee_salary )
    {
        this.employee_salary = employee_salary;
    }

    public int getAge()
    {
        return employee_age;
    }

    public void setAge( int employee_age )
    {
        this.employee_age = employee_age;
    }

    public String getProfileImage()
    {
        return profile_image;
    }

    public void setProfileImage( String profile_image )
    {
        this.profile_image = profile_image;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        Employ employ = (Employ) o;
        return id == employ.id &&
               employee_name.equals( employ.employee_name ) &&
               employee_salary.equals( employ.employee_salary ) &&
               employee_age == employ.employee_age;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id,
                             employee_name,
                             employee_salary,
                             employee_age );
    }

    @Override
    public String toString()
    {
        return "Employ{" +
               "id=" + id +
               ", employee_name='" + employee_name + '\'' +
               ", employee_salary='" + employee_salary + '\'' +
               ", employee_age=" + employee_age +
               ", profile_image='" + profile_image + '\'' +
               '}';
    }
}
