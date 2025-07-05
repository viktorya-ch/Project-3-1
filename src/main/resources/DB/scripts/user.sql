-- liquibase formatted sql

-- changeset vchb:1


<createIndex
indexName = "idx_student_name"
tableName = "Student">
<column name = "name"/>
</createIndex>


-- changeset vchb:2

<createIndex
indexName = "idx_faculty_name_color"
tableName = "Faculty">
<column name = "name"/>
<column name = "color"/>
</createIndex>