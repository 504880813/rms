/**
 * 添加卡片信息输入框节点
 * @param ismain
 * @param cardid
 * @param belongsCardid
 * @param username
 * @param phone
 * @param money
 * @param isedit
 */
function showInputdiv(ismain,cardid,belongsCardid,username,phone,money,isedit){
	//主卡
	if("true"==ismain){
		addMaincardInput(cardid,belongsCardid,username,phone,money,isedit);
	}
	//副卡
	if("false"==ismain){
		addVicecardInput(cardid,belongsCardid,username,phone,money,isedit);
	}
}
/**
 * 添加主卡信息框
 * @param cardid
 * @param belongsCardid
 * @param username
 * @param phone
 * @param money
 * @param isedit
 */
function addMaincardInput(cardid,belongsCardid,username,phone,money,isedit){
	//将原数据节点移除
	var messagediv=$("#cardMessage");
//	messagediv.children().remove();
	messagediv.empty();
	var html="";
	html+="卡号<input type='text' name='cardid'";
	html+=cardid==''?'':"value= "+cardid;
	if(isedit=="true"){
	html+="  readonly=readonly";
	}
	html+=" />";
	html+="用户名<input type='text' name='username' ";
	html+=username==''?'':"value= "+username;
	html+=" />";
	html+="电话<input type='text' name='phone' ";
	html+=phone==''?'':"value= "+phone;
	html+=" />";
	html+="开通金额<input type='text' name='money' ";
	html+=money==''?'':"value= "+money;
	html+=" />";
	messagediv.append(html);
}
/**
 * 添加副卡信息框
 * @param cardid
 * @param belongsCardid
 * @param username
 * @param phone
 * @param money
 * @param isedit
 */
function addVicecardInput(cardid,belongsCardid,username,phone,money,isedit){
	var messagediv=$("#cardMessage");
	messagediv.empty();
	var html="";
	html+="卡号<input type='text' name='cardid' ";
	html+=cardid==''?'':"value= "+cardid;
	if(isedit=="true"){
	html+="  readonly=readonly";
	}
	html+=" />";
	html+="主卡号<input type='text' name='belongsCardid' ";
	html+=belongsCardid==''?'':"value= "+belongsCardid;
	if(isedit=="true"){
	html+="  readonly=readonly";
	}
	html+=" />";
	html+="用户名<input type='text' name='username' ";
	html+=username==''?'':"value= "+username;
	html+=" />";
	html+="电话<input type='text' name='phone' ";
	html+=phone==''?'':"value= "+phone;
	html+=" />";
	messagediv.append(html);
}