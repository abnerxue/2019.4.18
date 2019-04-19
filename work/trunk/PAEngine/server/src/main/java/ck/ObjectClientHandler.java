package ck;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import ck.dto.DiagnosisDTO;
import ck.dto.MedcineDTO;
import ck.dto.PatientDTO;
import ck.dto.PrescriptionDTO;;

/**
 * @Author: zhuquanwen
 * @Description:
 * @Date: 2018/6/4 15:55
 * @Modified:
 **/
public class ObjectClientHandler extends ChannelInboundHandlerAdapter
{

	private PrescriptionDTO getTransportObject()
	{

		JsonParser myJsonParser = new JsonParser();
		PrescriptionDTO testPrescription1 = null;
		try
		{
			FileInputStream fs = new FileInputStream("PrescriptionDTO1.json");
			try
			{
				InputStreamReader in = new InputStreamReader(fs, "UTF-8");
				JsonObject prescriptionDTO1_JsonObject = (JsonObject) myJsonParser.parse(in);
				testPrescription1 = new PrescriptionDTO();
				testPrescription1.setState(prescriptionDTO1_JsonObject.get("state").getAsInt());
				// System.out.println(prescriptionDTO1_JsonObject.get("state").getAsInt());
				testPrescription1.setPrescriptionNumber(prescriptionDTO1_JsonObject.get("prescriptionNumber").getAsString());
				testPrescription1.setPrescriptionContent(prescriptionDTO1_JsonObject.get("prescriptionContent").getAsString());
				testPrescription1.setPrescriptionReason(prescriptionDTO1_JsonObject.get("prescriptionReason").getAsString());
				testPrescription1.setVisitNumber(prescriptionDTO1_JsonObject.get("visitNumber").getAsString());
				try
				{
					testPrescription1.setSubmmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(prescriptionDTO1_JsonObject.get("submmitTime").getAsString()));
					// System.out.println(new SimpleDateFormat("yyyy-MM-dd
					// HH:mm:ss").parse(prescriptionDTO1_JsonObject.get("submmitTime").getAsString()));
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				testPrescription1.setAuditPharmacist(prescriptionDTO1_JsonObject.get("auditPharmacist").getAsString());
				try
				{
					testPrescription1.setAuditTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(prescriptionDTO1_JsonObject.get("auditTime").getAsString()));
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				testPrescription1.setComfirmPharmacist(prescriptionDTO1_JsonObject.get("comfirmPharmacist").getAsString());
				try
				{
					testPrescription1.setComfirmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(prescriptionDTO1_JsonObject.get("comfirmTime").getAsString()));
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				testPrescription1.setType(prescriptionDTO1_JsonObject.get("type").getAsInt());
				testPrescription1.setAuditCenter(prescriptionDTO1_JsonObject.get("auditCenter").getAsString());
				testPrescription1.setMedicalUnit(prescriptionDTO1_JsonObject.get("medicalUnit").getAsString());
				testPrescription1.setDenyReason(prescriptionDTO1_JsonObject.get("denyReason").getAsString());
				testPrescription1.setDenyOwner(prescriptionDTO1_JsonObject.get("denyOwner").getAsString());
				testPrescription1.setDenyLevel(prescriptionDTO1_JsonObject.get("denyLevel").getAsString());
				testPrescription1.setDoctorName(prescriptionDTO1_JsonObject.get("doctorName").getAsString());
				testPrescription1.setDepartmentName(prescriptionDTO1_JsonObject.get("departmentName").getAsString());
				testPrescription1.setIsEmergency(prescriptionDTO1_JsonObject.get("isEmergency").getAsBoolean());

				JsonObject patientJO = prescriptionDTO1_JsonObject.get("patientInfo").getAsJsonObject();
				PatientDTO patient = new PatientDTO();
				// System.out.println(patientJO.get("name").getAsString());
				patient.setName(patientJO.get("name").getAsString());
				patient.setGender(patientJO.get("gender").getAsBoolean());
				patient.setAge(patientJO.get("age").getAsInt());
				patient.setAddress(patientJO.get("address").getAsString());
				patient.setAllergyHistory(patientJO.get("allergyHistory").getAsString());
				patient.setWeight(patientJO.get("weight").getAsFloat());
				patient.setBirthday(patientJO.get("birthday").getAsString());
				testPrescription1.setPatientInfo(patient);

				testPrescription1.setTotalFee(prescriptionDTO1_JsonObject.get("totalFee").getAsDouble());
				try
				{
					testPrescription1.setPrescriptionDate(new SimpleDateFormat("yyyy-MM-dd").parse(prescriptionDTO1_JsonObject.get("prescriptionDate").getAsString()));
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}

				JsonArray diagnosisDTOJOs = prescriptionDTO1_JsonObject.get("diagnosis").getAsJsonArray();
				List<DiagnosisDTO> diagnosisDTOs = new ArrayList<DiagnosisDTO>();

				for (JsonElement jsonElement : diagnosisDTOJOs)
				{
					DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
					diagnosisDTO.setDiagnosisName(((JsonObject) jsonElement).get("diagnosisName").getAsString());
					diagnosisDTO.setIcd10(((JsonObject) jsonElement).get("icd10").getAsString());
					diagnosisDTOs.add(diagnosisDTO);
				}
				testPrescription1.setDiagnosis(diagnosisDTOs);

				JsonArray medcineDTOJOs = prescriptionDTO1_JsonObject.get("medcineItems").getAsJsonArray();
				List<MedcineDTO> medcineItems = new ArrayList<MedcineDTO>();
				for (JsonElement jsonElement : medcineDTOJOs)
				{
					MedcineDTO medcineItem1 = new MedcineDTO();
					medcineItem1.setCommonName(jsonElement.getAsJsonObject().get("commonName").getAsString());
					medcineItem1.setSpecification(jsonElement.getAsJsonObject().get("specification").getAsString());
					medcineItem1.setSpecification(jsonElement.getAsJsonObject().get("specification").getAsString());
					medcineItem1.setEachDose(jsonElement.getAsJsonObject().get("eachDose").getAsDouble());
					medcineItem1.setEachDoseUnit(jsonElement.getAsJsonObject().get("eachDoseUnit").getAsString());
					medcineItem1.setTotal(jsonElement.getAsJsonObject().get("total").getAsDouble());
					medcineItem1.setTotalUnit(jsonElement.getAsJsonObject().get("totalUnit").getAsString());
					medcineItem1.setRouteOfMedication(jsonElement.getAsJsonObject().get("routeOfMedication").getAsString());
					medcineItem1.setFrequency(jsonElement.getAsJsonObject().get("frequency").getAsString());
					medcineItem1.setPrice(jsonElement.getAsJsonObject().get("price").getAsDouble());
					medcineItem1.setDuration(jsonElement.getAsJsonObject().get("duration").getAsInt());
					medcineItem1.setDailyDose(jsonElement.getAsJsonObject().get("dailyDose").getAsInt());
					medcineItems.add(medcineItem1);
				}

				testPrescription1.setMedcineItems(medcineItems);
			}
			catch (UnsupportedEncodingException e1)
			{
				e1.printStackTrace();
			}

		}
		catch (JsonIOException e)
		{
			e.printStackTrace();
		}
		catch (JsonSyntaxException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return testPrescription1;
	}

	// private JsonObject getStr()
	// {
	//
	// JsonParser myJsonParser = new JsonParser();
	//
	// JsonObject prescriptionDTO2_JsonObject = null;
	// try
	// {
	// prescriptionDTO2_JsonObject = (JsonObject) myJsonParser.parse(new
	// FileReader("PrescriptionDTO1.json"));
	// }
	// catch (JsonIOException e)
	// {
	// e.printStackTrace();
	// }
	// catch (JsonSyntaxException e)
	// {
	// e.printStackTrace();
	// }
	// catch (FileNotFoundException e)
	// {
	// e.printStackTrace();
	// }
	//
	// return prescriptionDTO2_JsonObject;
	//
	// }

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		System.out.println("active");
		super.channelActive(ctx);
		// 发送消息给服务端
		// ctx.writeAndFlush(getStr());
		ctx.writeAndFlush(getTransportObject());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception
	{
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		System.out.println(msg);
		System.out.println("Client Read");
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		// super.exceptionCaught(ctx, cause);
		System.out.println(cause.getMessage());
		ctx.close();
	}
}
