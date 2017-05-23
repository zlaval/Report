package com.zlrx.report;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReportRow {

  private String name;
  private Integer salary;
  private String address;
  private String job;

}
