package com.ucas.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TMData {
    // 注解: 把类和表映射起来.
    @Id
    private String f_name;
    @Column
    private String f_path;
    @Column
    private String loc;
    @Column
    private String sat;
    @Column
    private int path_id;
    @Column
    private int row_id;
    @Column
    private int time;
    @Column
    private String has_t;
    @Column
    private String has_ndvi;
}
