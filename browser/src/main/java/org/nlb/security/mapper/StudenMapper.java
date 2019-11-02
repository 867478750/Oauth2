package org.nlb.security.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.nlb.security.entity.Student;

@Mapper
public interface StudenMapper {
    @Select("select * from student where id = #{id}")
     Student getStudentById(Integer id);

    @Select("select * from student where name=#{name} ")
    Student getStudentByName(String name);

    @Insert("insert into student (name,password,ext) values (#{name},#{password},#{ext})")
    void insertStudentByStudent(Student student);


}
