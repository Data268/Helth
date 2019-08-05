package com.wd.doctor.common.utils.http;

import com.wd.doctor.common.bean.CircleInfoBean;
import com.wd.doctor.common.bean.CurrencyNotBean;
import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.bean.Department;
import com.wd.doctor.common.bean.DetailBean;
import com.wd.doctor.common.bean.DoctorBankBean;
import com.wd.doctor.common.bean.DoctorIdCardInfo;
import com.wd.doctor.common.bean.DoctorTitle;
import com.wd.doctor.common.bean.FindDoctorBean;
import com.wd.doctor.common.bean.FindSickBean;
import com.wd.doctor.common.bean.FindSystemImageBean;
import com.wd.doctor.common.bean.HistroyBean;
import com.wd.doctor.common.bean.InquiryBean;
import com.wd.doctor.common.bean.InquiryNotBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.bean.MyAdoptedBean;
import com.wd.doctor.common.bean.ReadNotBean;
import com.wd.doctor.common.bean.SearchcircleBean;
import com.wd.doctor.common.bean.SystemmessageBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ApiRequest {

    //邮箱
    @FormUrlEncoded
    @POST("health/doctor/v1/sendEmailCode")
    Observable<DataBean> getEmail(@Field("email") String email);

    //医生职称
    @GET("health/doctor/v1/findJobTitleList")
    Observable<DataBean<DoctorTitle>> getTitle();

    //申请入驻
//    @FormUrlEncoded
//    @POST("health/doctor/v1/applyJoin")
//    Observable<DataBean> getJoin(@Field("email")String email,@Field("code")String code,
//                                 @Field("pwd1")String pwd1,@Field("pwd2")String pwd2,
//                                 @Field("name")String name,@Field("inauguralHospital")String inauguralHospital,
//                                 @Field("departmentName")String departmentName,@Field("jobTitle")String jobTitle,
//                                 @Field("personalProfile")String personalProfile,@Field("goodField")String goodField
//
//    );
    @Headers({"Content-Type: application/json"})//需要添加头
    @POST("health/doctor/v1/applyJoin")
    Observable<DataBean> getJoin(@Body RequestBody route);

    //登录
    @FormUrlEncoded
    @POST("health/doctor/v1/login")
    Observable<DataBean<LoginBean>> getLogin(@Field("email") String email, @Field("pwd") String pwd);


    //校验验证码
    @FormUrlEncoded
    @POST("health/doctor/v1/checkCode")
    Observable<DataBean> getCheckcode(@Field("email") String email, @Field("code") String code);

    //充值用户密码
    @PUT("health/doctor/v1/resetUserPwd")
    Observable<DataBean> getReset(@Query("email") String email, @Query("pwd1") String pwd1, @Query("pwd2") String pwd2);

    //上传形象照
    @POST("health/doctor/verify/v1/uploadImagePic")
    Observable<DataBean> getUploadIam(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Body MultipartBody body);
//    Observable<ResponseBody> getUploadIam(@HeaderMap Map<String,String> map, @Part MultipartBody.Part part);

    //查询系统提供的形象照
    @GET("health/doctor/v1/findSystemImagePic")
    Observable<DataBean<List<FindSystemImageBean>>> getFindsyimg();

    //选择系统提供形象照
    @POST("health/doctor/verify/v1/chooseImagePic")
    Observable<DataBean> getChoose(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Body MultipartBody body);

    //根据医生Id查询医生信息
    @GET("health/doctor/verify/v1/findDoctorById")
    Observable<DataBean<FindDoctorBean>> getFindDoctor(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId);

    //查询科室列表
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<DataBean<List<Department>>> getDepartment();

    //病友圈列表展示
    @GET("health/doctor/sickCircle/v1/findSickCircleList")
    Observable<DataBean<List<FindSickBean>>> getFindSick(@Query("departmentId") int departmentId, @Query("page") int page, @Query("count") int count);

    //查询病友圈详情
    @GET("health/doctor/sickCircle/v1/findSickCircleInfo")
    Observable<DataBean <CircleInfoBean>> getCircleInfo(@Query("sickCircleId") int sickCircleId);

    //根据关键词查询病友圈
    @GET("health/doctor/sickCircle/v1/searchSickCircle")
    Observable<DataBean<List<SearchcircleBean>>> getSearchCircle(@Query("keyWord") String keyWord);

    //发表评论
    @FormUrlEncoded
    @POST("health/doctor/sickCircle/verify/v1/publishComment")
    Observable<DataBean> getPublish(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId,@Field("sickCircleId") int sickCircleId,@Field("content")String content);

    //查询医生系统通知消息
    @GET("health/doctor/verify/v1/findDoctorSystemNoticeList")
    Observable<DataBean<List<SystemmessageBean>>> getSystemEssage(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //查询问诊通知消息
    @GET("health/doctor/verify/v1/findDoctorInquiryNoticeList")
    Observable<DataBean<List<InquiryNotBean>>> getInquiryNot(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //查询H币通知消息
    @GET("health/doctor/verify/v1/findDoctorHealthyCurrencyNoticeList")
    Observable<DataBean<List<CurrencyNotBean>>> getCurrencyNot(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

//hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh

    //查询医生未读消息数
    @GET("health/doctor/verify/v1/findDoctorNoticeReadNum")
    Observable<DataBean<List<ReadNotBean>>> getReadNum(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId);

    //修改消息状态为全部已读
    @PUT("health/doctor/verify/v1/modifyAllStatus")
    Observable<DataBean> getStatus(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId);


    //我的被采纳的建议
    @GET("health/doctor/verify/v1/findMyAdoptedCommentList")
    Observable<DataBean<List<MyAdoptedBean>>> getMyAdoptedlist(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);


    //绑定银行卡
    @POST("health/doctor/verify/v1/bindDoctorBankCard")
    Observable<DataBean> getBankCard(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Field("bankCardNumber") String bankCardNumber, @Field("bankName") String bankName, @Field("bankCardType") String bankCardType);


    //绑定身份证
    @Headers({"Content-Type: application/json"})//需要添加头
    @POST("health/doctor/verify/v1/bindDoctorIdCard")
//    Observable<DataBean> getIdCard(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId
////            ,@Field("doctorId") int doctorId
//            , @Field("name") String name, @Field("sex") String sex, @Field("nation") String nation, @Field("birthday") String birthday, @Field("address") String address, @Field("idNumber") String idNumber, @Field("issueOffice") String issueOffice);


    Observable<DataBean> getIdCard(@Header("doctorId") String doctorId, @Header("sessionId")String sessionId, @Body RequestBody route);

    //查询医生身份证信息
    @GET("health/doctor/verify/v1/findDoctorIdCardInfo")
    Observable<DataBean<DoctorIdCardInfo>> getIdinfo(@Header("doctorId") String doctorId, @Header("sessionId")String sessionId);

    //查询医生银行卡信息
    @GET("health/doctor/verify/v1/findDoctorBankCardById")
    Observable<DataBean<DoctorBankBean>> getBank(@Header("doctorId") String doctorId, @Header("sessionId")String sessionId);


    //查询医生的问诊记录列表
    @GET("health/doctor/inquiry/verify/v1/findInquiryRecordList")
    Observable<DataBean<List<InquiryBean>>> getInquity(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId);

    //查询用户详细信息
    @GET("health/doctor/inquiry/verify/v1/findUserInfo")
    Observable<DataBean<DetailBean>> getDetail(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId, @Query("userId") int userId);

    //查询医生历史问诊记录列表
    @GET("health/doctor/inquiry/verify/v1/findHistoryInquiryRecord")
    Observable<DataBean<List<HistroyBean>>> getHistroy(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId,@Query("page")int page,@Query("count") int count);
}
