import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormateUtil {
	public static final String DATE_FORMAT_PAR = "yyyy-MM-dd";
	public static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PAR);
	
	public static  String formateTime(String time){
			
		
		String value = time.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("/", "-");
		if(time.length()==8&&time.indexOf('年')<0&&time.indexOf('/')<0&&time.indexOf('-')<0){
			value = time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8);
		}else if(time.length()>=9&&time.indexOf('年')<0&&time.indexOf('/')<0&&time.indexOf('-')<0){
			if(Long.parseLong(time)>315504000){ //大于1980
				value = sdf.format(new Date(Long.parseLong(time)));
			}
		}
		
		
		//补全位数
		String [] arr = value.split("-");
		String returnTime = arr[0];
		if(arr[1].length()!=2){
			returnTime=returnTime+"-0"+arr[1];
		}else{
			returnTime=returnTime+"-"+arr[1];
		}
		if(arr[2].length()!=2){
			returnTime=returnTime+"-0"+arr[2];
		}else{
			returnTime=returnTime+"-"+arr[2];
		}
		return returnTime;
		
	}
	
	public static void main(String[] args) {
		System.out.println(formateTime("2016年6月6日"));
	}
}
