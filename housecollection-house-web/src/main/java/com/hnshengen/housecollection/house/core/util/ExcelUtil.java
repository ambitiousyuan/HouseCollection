package com.hnshengen.housecollection.house.core.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


public class ExcelUtil {

   private  static   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    /**
     * 读取.xlsx 内容 
     * @param file
     * @return
     * @throws IOException
     */
    public static List<ArrayList<String>> readXlsx (MultipartFile file) {
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        InputStream input = null;
        XSSFWorkbook wb = null;
        try {
            input = file.getInputStream();
            //创建文档
            wb = new XSSFWorkbook(input);
            ArrayList<String> rowList = null;
            int totoalRows = 0;//总行数
            int totalCells = 0;//总列数
            //读取sheet(页)
            for (int sheetIndex = 0 ; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet xssfSheet = wb.getSheetAt(sheetIndex);

                if (xssfSheet == null) {
                    continue;
                }
                totoalRows = xssfSheet.getLastRowNum();
                //读取row
                for (int rowIndex = 0; rowIndex <= totoalRows; rowIndex++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowIndex);

                    if (xssfRow == null) {
                        continue;
                    }
                    rowList = new ArrayList<String>();
                    totalCells = xssfRow.getLastCellNum();

                    //读取列
                    for (int cellIndex = 0; cellIndex < totalCells; cellIndex++) {
                        XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                        if (xssfCell == null) {
                            rowList.add("");
                        } else {
                            xssfCell.setCellType(Cell.CELL_TYPE_STRING);
                            rowList.add(String.valueOf(xssfCell.getStringCellValue()).trim());
                        }
                    }

                    list.add(rowList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if ( wb != null) {
                    wb.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
            }
        }

        return list;
    }

    /**
     * 读取 .xls内容
     * @param file
     * @return
     * @throws IOException 
     */
    public static List<ArrayList<String>> readXls (MultipartFile file)  {
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        //创建输入流
        InputStream input = null;
        //创建文档
        HSSFWorkbook wb = null;

        try {
            input = file.getInputStream();
            //创建文档
            wb = new HSSFWorkbook(input);

            ArrayList<String> rowList = null;
            int totoalRows = 0;//总行数
            int totalCells = 0;//总列数
            //读取sheet(页)
            for (int sheetIndex = 0 ; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                HSSFSheet hssfSheet = wb.getSheetAt(sheetIndex);

                if (hssfSheet == null) {
                    continue;
                }

                totoalRows = hssfSheet.getLastRowNum();
                //读取row
                for (int rowIndex = 0; rowIndex <= totoalRows; rowIndex++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowIndex);

                    if (hssfRow == null) {
                        continue;
                    }
                    rowList = new ArrayList<String>();
                    totalCells = hssfRow.getLastCellNum();

                    //读取列
                    for (int cellIndex = 0; cellIndex < totalCells; cellIndex++) {
                        HSSFCell hssfCell = hssfRow.getCell(cellIndex);
                        if (hssfCell == null) {
                            rowList.add("");
                        } else {
                            hssfCell.setCellType(Cell.CELL_TYPE_STRING);
                            rowList.add(String.valueOf(hssfCell.getStringCellValue()).trim());
                        }
                    }

                    list.add(rowList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if ( wb != null) {
                    wb.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }
    
    
    /**
	 * @MethodName : excelToList
	 * @Description : 将Excel转化为List
	 * @param entityClass
	 *            ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
	 * @param fieldMap
	 *            ：Excel中的中文列头和类的英文属性的对应关系Map
	 * @return ：List
	 * @throws Exception
	 */
	public static <T> List<T> excelToList(MultipartFile file,
			Class<T> entityClass, LinkedHashMap<String, String> fieldMap)
			throws Exception {
		try {
			boolean isExcel2003 = true;

			if (isExcel2007(file.getOriginalFilename())) {
				isExcel2003 = false;
			}
			// 根据新建的文件实例化输入流
			InputStream is = file.getInputStream();

			List<T> list = new ArrayList<T>();
			// 根据excel里面的内容读取客户信息
			list = getExcelInfo(is, isExcel2003, fieldMap, entityClass);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	private static <T> List<T> getExcelInfo(InputStream is,
			boolean isExcel2003, LinkedHashMap<String, String> fieldMap,
			Class<T> entityClass) throws Exception {
		List<T> list = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			// 当excel是2003时
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时
				wb = new XSSFWorkbook(is);
			}
			// 读取Excel里面客户的信息
			list = readExcelValue(wb, fieldMap, entityClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 读取Excel里面客户的信息
	 * 
	 * @param wb
	 * @return
	 * @return
	 * @throws Exception
	 */
	private static <T> List<T> readExcelValue(Workbook wb,
			LinkedHashMap<String, String> fieldMap, Class<T> entityClass)
			throws Exception {
		List<T> list = new ArrayList<T>();
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 获取工作表的有效行数
		int realRows = sheet.getPhysicalNumberOfRows();
		int realColumn = 0;
		if (realRows > 0) {
			realColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		}

		String[] excelFieldNames = new String[realColumn];
		// 获取Excel中的列名
		for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
			excelFieldNames[i] = sheet.getRow(0).getCell(i)
					.getStringCellValue();
		}

		// 判断需要的字段在Excel中是否都存在
		boolean isExist = true;
		List<String> excelFieldList = Arrays.asList(excelFieldNames);
		for (String cnName : fieldMap.keySet()) {
			if (!excelFieldList.contains(cnName)) {
				isExist = false;
				break;
			}
		}

		// 如果有列名不存在，则抛出异常，提示错误
		if (!isExist) {
			throw new Exception("Excel中缺少必要的字段，或字段名称有误");
		}
		// 将列名和列号放入Map中,这样通过列名就可以拿到列号
		LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < excelFieldNames.length; i++) {
			colMap.put(excelFieldNames[i], sheet.getRow(0).getCell(i)
					.getColumnIndex());
		}

		// 将sheet转换为list
		for (int i = 1; i < realRows; i++) {
			// 新建要转换的对象
			T entity = entityClass.newInstance();

			// 给对象中的字段赋值
			for (Entry<String, String> entry : fieldMap.entrySet()) {
				// 获取中文字段名
				String cnNormalName = entry.getKey();
				// 获取英文字段名
				String enNormalName = entry.getValue();
				// 根据中文字段名获取列号
				int col = colMap.get(cnNormalName);

				Row row = sheet.getRow(i);
				Cell cell = row.getCell(col);
				// 获取当前单元格中的内容
				String content = getStringValueFromCell(cell);

				// 给对象赋值
				setFieldValueByName(enNormalName, content, entity);
			}

			list.add(entity);
		}
		return list;
	}
	
	
	/**
	 * @MethodName : setFieldValueByName
	 * @Description : 根据字段名给对象的字段赋值
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param o
	 *            对象
	 */
	private static void setFieldValueByName(String fieldName,
			Object fieldValue, Object o) throws Exception {

		Field field = getFieldByName(fieldName, o.getClass());
		if (field != null) {
			field.setAccessible(true);
			// 获取字段类型
			Class<?> fieldType = field.getType();

			// 根据字段类型给字段赋值
			if (String.class == fieldType) {
				field.set(o, String.valueOf(fieldValue));
			} else if ((Integer.TYPE == fieldType)
					|| (Integer.class == fieldType)) {
				field.set(o, Integer.parseInt(fieldValue.toString()));
			} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
				field.set(o, Long.valueOf(fieldValue.toString()));
			} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
				field.set(o, Float.valueOf(fieldValue.toString()));
			} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
				field.set(o, Short.valueOf(fieldValue.toString()));
			} else if ((Double.TYPE == fieldType)
					|| (Double.class == fieldType)) {
				field.set(o, Double.valueOf(fieldValue.toString()));
			} else if (Character.TYPE == fieldType) {
				if ((fieldValue != null)
						&& (fieldValue.toString().length() > 0)) {
					field.set(o,
							Character.valueOf(fieldValue.toString().charAt(0)));
				}
			} else if (Date.class == fieldType) {
				field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(fieldValue.toString()));
			} else {
				field.set(o, fieldValue);
			}
		} else {
			throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 "
					+ fieldName);
		}
	}
	
	private static Field getFieldByName(String fieldName, Class<?> clazz) {
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}
	
	private static String getStringValueFromCell(Cell cell) {
		SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#.#");
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			cellValue = cell.getStringCellValue();
		}

		else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				double d = cell.getNumericCellValue();
				Date date = HSSFDateUtil.getJavaDate(d);
				cellValue = sFormat.format(date);
			} else {
				cellValue = decimalFormat.format((cell.getNumericCellValue()));
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			cellValue = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
			cellValue = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			cellValue = cell.getCellFormula().toString();
		}
		return cellValue;
	}
	
	 // @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xls)$");  
     }  
   
    //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xlsx)$");  
     } 


    //用户Excel模板下载
    public static void downBusLotNum(OutputStream os
			) throws Exception {
		// 创建工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 在工作薄上建一张工作表
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow((short) 0);
		sheet.createFreezePane(0, 1);
		cteateCell(wb, row, (short) 0, "客户姓名");
		cteateCell(wb, row, (short) 1, "认筹号");
		cteateCell(wb, row, (short) 2, "联系方式（手机号）");
		cteateCell(wb, row, (short) 3, "证件号");
		wb.write(os);
		os.flush();
		os.close();
		System.out.println("用户excel模板文件生成");
	}
    
  
    

    
    private static void cteateCell(HSSFWorkbook wb, HSSFRow row, short col,
			String val) {
		HSSFCell cell = row.createCell(col);
		cell.setCellValue(val);
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(cellstyle);
	}
    /**
     * 转用于导出excel
     * @param wb
     * @param row
     * @param col
     * @param val
     */
    private static void exportCell(HSSFWorkbook wb, HSSFRow row, short col,
    		String val,HSSFCellStyle cellstyle) {
    	HSSFCell cell = row.createCell(col);
    	cell.setCellValue(val);
    	cell.setCellStyle(cellstyle);
    }



}
