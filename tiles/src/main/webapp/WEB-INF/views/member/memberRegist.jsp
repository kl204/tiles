<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link href="../resources/css/home.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
			$('#btn_check_id').on('click',function(e){
				let memberId = $('#memberId').val();
				
				console.log(memberId);				
				
				if(memberId!=null || memberId!=''){
					 $.ajax({
				    	    type: "GET", 
				    	    url: "./id_validation?memberId=" + memberId, 
				    	    dataType: "json",
				    	    success: function (data) {
				    		  console.log("success");
				    	      console.log(data);
				    	      
				    	      const borrowResult = data.result;
				    	  	const flags = document.getElementById("flag");
				    	      
				    	      console.log(borrowResult);
			    	      
 				    	      if (borrowResult==true) {
				    	        document.getElementById("message1").innerHTML = "아이디가 존재합니다";
				    	        flags.value = false;

				    	      } else {
				    	        document.getElementById("message1").innerHTML = "아이디 생성 가능합니다";
				    	        flags.value = true;
				    	      } 
				    	    },error: function(err){
				    	    	
				    	    	console.log(err);
				    	  
				    	    }
				    	    
				    	    
				    	  });
				} else {
					$('#message1').html("id가 비어있거나 형식에 맞지 않습니다.");
				}

			});
			
			
			
			let checkId = function(){
				return $('#flag').val();
			}
			
		
			let validateData = function(){
				let flag = false;
				
	    	  	const flags = document.getElementById("flag");

	    	  	
				if(checkId()=='true'){
					flag = true;
					$('#message1').html('id가 형식에 올바릅니다.');

				} else {
					$('#message1').html('id가 비어있거나 형식에 맞지 않습니다.2');
				}
				return flag;
			};
			
			
			let confirm = () => {
				let result = false;
				
				const pwd1 = document.getElementById("pwd1").value;
				const pwd2 = document.getElementById("pwd2").value;

				if(pwd1 ==null || pwd1 =='' ){
					$('#message2').html('비번이 비어있습니다');
				}else{
					
					if(pwd1 == pwd2){
						$('#message2').html('비번이 일치합니다.');
						result = true;
					}else {
						$('#message2').html('비번이 일치하지 않습니다.');
					}
					
				}
				

				return result;
			};
			
			// 관심분야 2~3개 선택
			let count = function(){
				result = false;
				
				const interest =document.getElementsByName("interests");
				let interestNum = 0;
				
				Array.from(interest).forEach(ele => {
					  if (ele.checked) {
					    interestNum++;
					  }
					});
				
				
				if(interestNum > 1 && interestNum < 4){
					result= true;
					$('#message3').html('선택 잘했다인마');

				}else{
					$('#message3').html('2~3개 사이 선택해야합니다.');
				}
				
				return result;
			};
			
			$('#sending').on('click',function(e){
				let result = validateData();
				result = confirm() && result;
				result = count() && result;
				if(!result){
					console.log("아직 등록 못함");
					e.preventDefault();
				}else{
                    $('#frm').attr('action','/QuizSampleTest/BlmController?cmd=list');
			        $('#frm').submit();	                
			            

			        					
					
				}			
				
			});
		});
	</script>
</head>
<body>
<form action="" method="post" id="frm">
    <table>
        <tr><th colspan="3" id="form">회원가입양식</th></tr>
        <tr>
        	<th>구분</th>
        	<th class="data_ui" colspan="1">데이터입력</th>
        	<th>비고</th>
        </tr>
        <tr>
            <td>아이디</td>
            <td colspan="1">
            	<input type="text" id="memberId" name="memberId">
            	<button type="button" id="btn_check_id">아이디확인</button>
            </td>
            <td id="message1"></td>
        </tr>
        <tr>
        	<td>비밀번호</td>
        	<td colspan="1"><input type="password" id="pwd1" name="pwd"></td>
        	<td id="message2"></td>
        </tr>
        <tr>
        	<td>비번확인</td>
        	<td colspan="1"><input type="text" id="pwd2"></td>
        	<td id=""></td>
        </tr>
        <tr>
            <td>성별</td>
            <td colspan="1">
                <input type="radio" name="gender" value="M">남
                <input type="radio" name="gender" value="F" checked>여
            </td>
            <td></td>
        </tr>
        <tr>
            <td>관심분야</td>
            <td colspan="1">
                <input type="checkbox" name="interests" value="0">문학
                <input type="checkbox" name="interests" value="1">어학
                <input type="checkbox" name="interests" value="2" checked>정보IT<br>
                <input type="checkbox" name="interests" value="3">과학
                <input type="checkbox" name="interests" value="4">수학
                <input type="checkbox" name="interests" value="5">예술
            </td>
            <td id="message3"></td>
        </tr>
        <tr>
        	<td>자기소개</td>
        	<td colspan="1"><textarea cols="50" rows="5" name="introduce"></textarea></td>
        	<td></td>
        </tr>    
        <tr id="footer">
        	<td colspan="3"><input type="submit" value="가입" id="sending"> <input type="reset"></td>
        </tr>
    </table>
    <input type="hidden" id="flag" value="false">
</form>
</body> 
</html>