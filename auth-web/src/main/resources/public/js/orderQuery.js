
function changeBusiness(business) {
    var trs = $("#resultTable thead");
    trs.empty();
    $("#resultBody").empty();
    var headHtml = "";
    removeActive();
    $("#taskType").val(business);
    if(business=='member-order'){
        $("#member-order").attr("class","active");
        headHtml ="<tr ><th>商户号</th>" +
            "<th>商户订单号</th>" +
            "<th>商户订单时间</th>" +
            "<th>产品编号</th>" +
            "<th>系统流水号</th>" +
            "<th>业务流水号</th>"+
            "<th>订单状态</th>" +
            "<th>订单时间</th>" +
            "<th>收费标识</th>"+
            "<th>响应编码</th>"+
            "<th>响应描述</th></tr>";
        $("#resultTable thead").append(headHtml);
        $("#resultTable thead").trigger("create");
        $("#city_code_lab").text("地区编码");
        $("#city_lab").text("地区名称");
        $("#carrier_lab").hide();
        $("#mobile_select").hide();
        $("#card_select").hide();
    }
    if(business=='channel-order'){
        $("#channel-order").attr("class","active");
        headHtml ="<tr ><th>渠道id</th>" +
            "<th>业务流水号</th>" +
            "<th>系统流水号</th>" +
            "<th>渠道计费标识</th>" +
            "<th>渠道耗时</th>" +
            "<th>渠道请求流水</th>"+
            "<th>渠道响应代码</th>" +
            "<th>渠道响应描述</th>" +
            "<th>渠道响应流水</th>"+
            "<th>订单时间</th></tr>";
        $("#resultTable thead").append(headHtml);
        $("#resultTable thead").trigger("create");
        $("#city_code_lab").text("地区编码");
        $("#city_lab").text("地区名称");
        $("#carrier_lab").hide();
        $("#mobile_select").hide();
        $("#card_select").hide();
    }
    if(business=='member-order-count'){
        $("#member-order-count").attr("class","active");
        headHtml ="<tr ><th >商户号</th>" +
            "<th >产品id</th>" +
            "<th >订单状态</th>" +
            "<th >订单数</th>" +
            "<th >订单金额</th>" +
            "<th >计费标识</th>" +
            "<th >响应code</th></tr>";
        $("#resultTable thead").append(headHtml);
        $("#resultTable thead").trigger("create");
        $("#city_code_lab").text("城市编码");
        $("#city_lab").text("城市名称");
        $("#carrier_lab").show();
        $("#carrier_lab").text("运营商");
        $("#mobile_select").show();
        $("#card_select").hide();
    }
    if(business=='channel-order-count'){
        $("#channel-order-count").attr("class","active");
        headHtml ="<tr ><th >渠道id</th>" +
            // "<th >产品id</th>" +
            // "<th >订单状态</th>" +
            "<th >订单数</th>" +
            // "<th >订单金额</th>" +
            "<th >计费标识</th>" +
            "<th >响应code</th></tr>";
        $("#resultTable thead").append(headHtml);
        $("#resultTable thead").trigger("create");
        $("#city_code_lab").text("城市编码");
        $("#city_lab").text("城市名称");
        $("#carrier_lab").show();
        $("#carrier_lab").text("运营商");
        $("#mobile_select").show();
        $("#card_select").hide();
    }
}

function removeActive() {
    $("#function-BANK").removeClass();
    $("#function-FUND").removeClass();
    $("#function-CARRIER").removeClass();
    $("#function-SECURITY").removeClass();
}

function checkBusiness() {
    var taskType = $("#taskType").val();
    if(processNull(taskType)==null){
        alert("请先选择业务类型");
        return false;
    }
    return true;
}

function queryConfig() {
    if(!checkBusiness()){
        return
    }
    var taskType = $("#taskType").val();

    var city_code = processNull($("#city_code").val()),
        city_name = processNull($("#city_name").val()),
        status = processNull($("#status_select").val()),
        carrierCode = processNull($("#mobile_select").val()),
        cardType = processNull($("#card_select").val());
    var url='/spider-monitor/config/'+taskType+'/queryByPage',
        data={
            areaCode:city_code,
            areaName:city_name,
            provCode:city_code,
            provName:city_name,
            bankCode:city_code,
            bankName:city_name,
            cardType:cardType,
            carrierCode:carrierCode,
            status:status,
            page:1,
            pageSize:1000,
        };

    $.ajax({
        url:url,
        data:JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        type:"POST",
        success:function(result){
            if(result.success) {
                addResult(result.data,taskType);
            }else {
                alert(result.errorMsg);
            }
        }
    });
}

function processNull(obj) {
    if(obj==''||obj==undefined||obj=='ALL'){
        return null;
    }
    return obj;
}


/** 添加任务历史信息*/
function addResult(responseData,taskType) {
    if (responseData == null) {
        $("#resultBody").empty();
        return;
    }
    var resultArray = responseData.result;
    if(resultArray==null||resultArray.length<1){
        alert("未查询到结果");
        return;
    }
    //检查
    $("#resultBody").empty();
    for (var i = 0; i < resultArray.length; i++) {
        //检查resultArray里的taskDetailInfo
        var detailInfo = resultArray[i];
        //字段转换
        detailInfo.index = i + 1;
        if(taskType=='carrier'){
            detailInfo.areaCode=detailInfo.provCode;
            detailInfo.areaName=detailInfo.provName;
            detailInfo.webUrl=detailInfo.carrierUrl;
            detailInfo.status=detailInfo.carrierStatus;
            detailInfo.subCode=detailInfo.carrierCode;
            detailInfo.subName=detailInfo.carrierName;
            $.template("resultTmpl", $('#resultTmpl').html());
            $.tmpl("resultTmpl", detailInfo).appendTo("#resultBody");
        }
        
        if(taskType=='fund'||taskType=='security'){
            $.template("resultTmpl", $('#resultTmpl').html());
            $.tmpl("resultTmpl", detailInfo).appendTo("#resultBody");
        }
        if(taskType=='bank'){
            detailInfo.areaCode=detailInfo.bankCode;
            detailInfo.areaName=detailInfo.bankName;
            detailInfo.subCode=detailInfo.cardType;
            detailInfo.subName=detailInfo.cardName;
            $.template("resultTmpl", $('#resultTmpl').html());
            $.tmpl("resultTmpl", detailInfo).appendTo("#resultBody");
        }
    }
}

function openDiv(flag) {
    if(!checkBusiness()){
        return
    }
    clearDiv();
    $("#detail_div").show();
    var id = $('input[name="check_col"]:checked').val();
    var taskType = $("#taskType").val();
    if(taskType=='carrier'){
        // $("#au_carrier_lab").show();
        // $("#mobile_code").show();
        $("#carrier_li").show();
        $("#card_type_li").hide();
        $("#au_city_code_lab").text("城市编码");
        $("#au_city_name_lab").text("城市名称");
    }else if(taskType=='bank'){
        $("#carrier_li").hide();
        $("#card_type_li").show();
        $("#au_city_code_lab").text("银行编码");
        $("#au_city_name_lab").text("银行名称");
    }else {
        // $("#au_carrier_lab").hide();
        // $("#mobile_code").hide();
        $("#carrier_li").hide();
        $("#card_type_li").hide();
        $("#au_city_code_lab").text("地区编码");
        $("#au_city_name_lab").text("地区名称");
    }
    if(flag){
        $("#add_btn").val("新增");
    }else {
        if(id==null||id==undefined||id==''){
            alert("请先选择需要修改的配置");
            closeDiv();
            return;
        }
        $("#add_btn").val("更新");
        getUpdateData(taskType,id);
    }
}

function getUpdateData(taskType,id) {
    var url='/spider-monitor/config/'+taskType+'/queryById/'+id;
    $.ajax({
        url:url,
        data:null,
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        type:"POST",
        success:function(result){
            if(result.success) {
                setUpdateData(result.data,taskType);
            }else {
                alert(result.errorMsg);
            }
        }
    });
}

function setUpdateData(data,taskType) {
    $("#col_id").val(data.id);
    if(taskType=="carrier") {
        $("#area_code").val(data.provCode);
        $("#area_name").val(data.provName);
        $("#config_status").val(data.carrierStatus);
        $("#web_url").val(data.carrierUrl);
        $("#mobile_code").val(data.carrierCode);
    }
    if(taskType=='fund'||taskType=='security'){
        $("#area_code").val(data.areaCode);
        $("#area_name").val(data.areaName);
        $("#config_status").val(data.status);
        $("#web_url").val(data.webUrl);
        $("#mobile_code").val(data.carrierCode);
    }
    if(taskType=='bank'){
        $("#area_code").val(data.bankCode);
        $("#area_name").val(data.bankName);
        $("#config_status").val(data.status);
        $("#web_url").val(data.webUrl);
        $("#card_type").val(data.cardType);
    }
}

function clearDiv() {
    $("#col_id").val("");
    $("#area_code").val("");
    $("#area_name").val("");
    $("#config_status").val("CLOSE");
    $("#web_url").val("");
    $("#mobile_code").val("dx");
    $("#card_type").val("DEBIT_CARD");
}

function closeDiv() {
    clearDiv();
    $("#detail_div").hide();
}

function addAndUpdate() {
    var url='',
        taskType = $("#taskType").val(),
        id =  processNull($("#col_id").val());
    if(id==null){
        url = '/spider-monitor/config/'+taskType+'/addConfig'
    }else {
        url = '/spider-monitor/config/'+taskType+'/updateById';
    }
    var city_code = processNull($("#area_code").val()),
        city_name = processNull($("#area_name").val()),
        status = processNull($("#config_status").val()),
        carrierCode = processNull($("#mobile_code").val()),
        carrierName = processNull($("#mobile_code").find("option:selected").text()),
        cardCode = processNull($("#card_type").val()),
        cardName = processNull($("#card_type").find("option:selected").text());
    var data = {
        id:id,
        areaCode:city_code,
        areaName:city_name,
        provCode:city_code,
        provName:city_name,
        carrierCode:carrierCode,
        carrierName:carrierName,
        bankCode:city_code,
        bankName:city_name,
        cardType:cardCode,
        cardName:cardName,
        status:status,
        carrierStatus:status,
        webUrl:$("#web_url").val(),
        carrierUrl:$("#web_url").val(),
    };
    $.ajax({
        url:url,
        data:JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        type:"POST",
        success:function(result){
            if(result.success) {
               if(result.data){
                   alert("处理成功");
                   closeDiv();
               }else {
                   alert("处理失败");
               }
            }else {
                alert(result.errorMsg);
            }
        }
    });
}