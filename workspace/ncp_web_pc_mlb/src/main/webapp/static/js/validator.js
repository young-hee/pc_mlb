var Validator = {
	/**
	 *  에러메세지를 노출할 위치는 파라미터로 받지 않으면
	 *  $(this).parent().find("span.error-msg"); 또는 $(this).parent().find("p.error-msg"); 로 세팅됨.
	 * 
	 * form
	 * title
	 * layer : 에러메세지를 layer로 표시할지 여부(true : layer 표시, false or null : layer 미표시)
	 * focus : focus 이동 여부(true : 이동, false or null : 미이동)
	 * spanParam : 메세지를 출력할 attribute(없으면 기본 사용)
	 * 
	 * 에러메세지 추출식.(순서대로 있는 것을 먼저 사용)
	 * 1. validate를 설정한 attribute의 validateMsg 값
	 * 2. validate를 설정한 attribute의 $("label[for='" + j.attr("id") + "']"); 값 + 공통메세지
	 * 3. validate를 설정한 attribute의 validateText 값 + 공통메세지
	 */
	validate : function(form, title /* optional */, layer, focus, spanParam) {

		this.currentForm = form;
		var sucess = true;
		var loc;
		var span;
		
		$(form).find(":input[validate]").each(function(i) {
			var j = $(this);
			var label = $("label[for='" + j.attr("id") + "']");
			if(spanParam == undefined || spanParam.length == 0) {
				span = $(this).parent().find(".error-msg");
			}
			if(span == undefined || span.length == 0) {
				span = $(this).parent().parent().find(".error-msg");
			}
			
			if(!loc){
				loc = $('html').attr('lang');
			}

			if(!loc) {
				loc = j.attr("locale");
			}

			if(!loc){
				loc = "ko";
			}

			var vrs = j.attr("validate");
			var rs = Validator.parseInlineVrs(vrs);

			for ( var n in rs) {

				if (n != "label") {
					var param = rs[n];
					var result = Validator.rules[n].check.call(Validator, j.val(), this, param);

					if (result != true) {
						var msg = "";
						var text = "";
						
						// 1. validateMsg
						text = j.attr("validateMsg");
						
						// 2. $("label[for='" + j.attr("id") + "']"); 값 + 공통메세지
						if(text == undefined || text == "") {
							text = label.text();
							
							// 3. validateText
							if(text == undefined || text == "") {
								text = j.attr("validateText");
							}
							
							msg = Validator.formatMessage(Validator.locales[loc].messages[n],param);
							msg = msg.replace(/'T'/g, "\""+text + "\"");
						}

						if (title == null) {
							title = "Alert";
						}
						
						if (layer == null || layer == undefined) {
							$(span).text(msg);
							errorMsgShow(span);
						}
						else {
							alertLayer(msg);							
						}

						sucess = false;
						if(focus) {
							if(j.attr("type") == "hidden") {
								setTimeoutFocus(j.parent().find("[type=text]").eq(0).attr("id"));
							}
							else {
								setTimeoutFocus(j.attr("id"));	
							}
						}
						return false;
					}
					else {
						if (layer == null || layer == undefined) {
							errorMsgHide(span);
						}
					}
				}
			}

			return true;
		});

		return sucess;
	},

	/**
	 *  에러메세지를 노출할 위치는 파라미터로 받지 않으면
	 *  $(this).parent().find("span.error-msg"); 또는 $(this).parent().find("p.error-msg"); 로 세팅됨.
	 * 
	 * form
	 * title
	 * layer : 에러메세지를 layer로 표시할지 여부(true : layer 표시, false or null : layer 미표시)
	 * focus : focus 이동 여부(true : 이동, false or null : 미이동)
	 * spanParam : 메세지를 출력할 attribute(없으면 기본 사용)
	 * validateAttribute : 검증할 개별 attribute(필수) ex) $("#id")
	 * 
	 * 에러메세지 추출식.(순서대로 있는 것을 먼저 사용)
	 * 1. validate를 설정한 attribute의 validateMsg 값
	 * 2. validate를 설정한 attribute의 $("label[for='" + j.attr("id") + "']"); 값 + 공통메세지
	 * 3. validate를 설정한 attribute의 validateText 값 + 공통메세지
	 */
	validateField : function(form, title /* optional */, layer, focus, spanParam, validateAttribute) {

		this.currentForm = form;
		var sucess = true;
		var loc;
		var span;
		
		var j = validateAttribute;
		var label = $("label[for='" + validateAttribute.attr("id") + "']");
		if(spanParam == undefined || spanParam.length == 0) {
			span = j.parent().find("span.error-msg");
		}
		if(span == undefined || span.length == 0) {
			span = j.parent().find("p.error-msg");
		}
		if(span == undefined || span.length == 0) {
			span = j.parent().parent().find(".error-msg");
		}

		if(!loc){
			loc = $('html').attr('lang');
		}

		if(!loc) {
			loc = j.attr("locale");
		}

		if(!loc){
			loc = "ko";
		}

		var vrs = j.attr("validate");
		var rs = Validator.parseInlineVrs(vrs);

		for ( var n in rs) {

			if (n != "label") {
				var param = rs[n];
				var result = Validator.rules[n].check.call(Validator, j.val(), j[0], param);

				if (result != true) {
					var msg = "";
					var text = "";
					
					// 1. validateMsg
					text = j.attr("validateMsg");
					
					// 2. $("label[for='" + j.attr("id") + "']"); 값 + 공통메세지
					if(text == undefined || text == "") {
						text = label.text();
						
						// 3. validateText
						if(text == undefined || text == "") {
							text = j.attr("validateText");
						}
						
						msg = Validator.formatMessage(Validator.locales[loc].messages[n],param);
						msg = msg.replace(/'T'/g, "\""+text + "\"");
					}

					if (title == null) {
						title = "Alert";
					}
					
					if (layer == null || layer == undefined) {
						$(span).text(msg);
						errorMsgShow(span);
					}
					else {
						alertLayer(msg);							
					}

					sucess = false;
					if(focus) {
						if(j.attr("type") == "hidden") {
							setTimeoutFocus(j.parent().find("[type=text]").eq(0).attr("id"));
						}
						else {
							setTimeoutFocus(j.attr("id"));	
						}
					}
					return false;
				}
				else {
					if (layer == null || layer == undefined) {
						errorMsgHide(span);
					}
				}
			}
		}

		return sucess;
	},

	parseInlineVrs : function(vrs) {
		var rs = new Object();

		var ss = vrs.split(";");
		for (var i = 0; i < ss.length; ++i) {
			var r = $.trim(ss[i]);
			if (r) {
				var rr = r.split(":");

				var ruleName = $.trim(rr[0]);
				var ruleValue = $.trim(rr[1]);

				if (Validator.rules[ruleName]
						&& Validator.rules[ruleName].argc > 1) {
					var ruleValueCount = Validator.rules[ruleName].argc;
					var multiRuleValue = ruleValue.split(new RegExp(" +", "g"),
							ruleValueCount);
					for (var j = 0; j < multiRuleValue.length; ++j) {
						multiRuleValue[j] = eval(multiRuleValue[j]);
					}
					rs[ruleName] = multiRuleValue;
				} else {
					rs[ruleName] = eval(ruleValue);
				}
			}
		}

		return rs;
	},

	parseVrs : function(vrs) {
	},

	formatMessage : function(format, param) {
		if (arguments.length <= 1) {
			return format;
		}

		var s = format;
		if (param instanceof Array) {
			for (var i = 0; i < param.length; ++i) {
				s = s.replace(new RegExp("\\{" + i + "\\}", "g"), param[i]);
			}
		} else {
			for (var i = 1; i < arguments.length; ++i) {
				s = s.replace(new RegExp("\\{" + (i - 1) + "\\}", "g"),
						arguments[i]);
			}
		}

		return s;
	},

	getLength : function(value, element) {
		switch (element.nodeName.toLowerCase()) {
		case 'select':
			return $("option:selected", element).length;
		case 'input':
			if (this.checkable(element))
				return Validator.findByName(element.name).filter(':checked').length;
		}
		return value.length;
	},

	getByteLength : function(value, element) {
		switch (element.nodeName.toLowerCase()) {
		case 'select':
			return $("option:selected", element).length;
		case 'input':
			if (Validator.checkable(element))
				return Validator.findByName(element.name).filter(':checked').length;
		}
		return value.length;
	},

	getBytesLength : function(value, element) {
		switch (element.nodeName.toLowerCase()) {
		case 'select':
			return $("option:selected", element).length;
		case 'input':
			if (Validator.checkable(element))
				return Validator.findByName(element.name).filter(':checked').length;
		}
		var tcount = 0;
		var tmpStr = new String(value); // value.replace("\n","%OA");// new
										// String(value);
		var temp = tmpStr.length;
		var onechar;

		for (var i = 0; i < temp; i++) {
			onechar = tmpStr.charAt(i);
			if (/(\á|\í|\é|\ó|\ú|\ý|\ã|\õ|\ç|\à|\è|\ì|\ò|\ù|\â|\ê|\î|\ô|\û|\ä|\ë|\ï|\ö|\ü|\ÿ|\¡|\¿|\À|\Ä|\Ç|\È|\É|\Ö|\Ü|\Ñ|\ñ|\œ|\β|\ß|\§)$/i
					.test(onechar)) {
				tcount += 2;
			} else if (escape(onechar).length > 4) { // 한글일때 3바이트 적용
				tcount += 3;
			} else if (onechar == "\n") {
				// if(tmpStr.charAt(i-1) != "\r"){
				tcount += 4;
				// }
			} else if (onechar == "<" || onechar == ">") {
				tcount += 4;
			} else {
				tcount += 1;
			}
		}
		// alert(" tcount : " + tcount);
		return tcount;
	},

	depend : function(param, element) {
		// alert(param + " : " + typeof param);
		return Validator.dependTypes[typeof param] ? Validator.dependTypes[typeof param]
				(param, element)
				: true;
	},

	dependTypes : {
		"boolean" : function(param, element) {
			return param;
		},
		"number" : function(param, element) {
			return param;
		},
		"string" : function(param, element) {
			return !!$(param, element.form).length;
		},
		"function" : function(param, element) {
			return param(element);
		}
	},

	checkable : function(element) {
		return /radio|checkbox/i.test(element.type);
	},

	findByName : function(name) {
		// select by name and filter by form for performance over
		// form.find("[name=...]")
		var form = Validator.currentForm;
		return $(document.getElementsByName(name)).map(
				function(index, element) {
					return element.form == form && element.name == name
							&& element || null;
				});
	},

	optional : function(element) {
		return !Validator.rules.required.check.call(Validator, $
				.trim(element.value), element);
	},

	rules : {
		required : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {
				// check if dependency is met
				if (!Validator.depend(param, element)) {
					return false;
				}

				switch (element.nodeName.toLowerCase()) {
				case 'select':
					// could be an array for select-multiple or a string, both
					// are fine this way
					var val = $(element).val();
					return val && val.length > 0;
				case 'input':
					if (Validator.checkable(element))
						return Validator.getLength(value, element) > 0;
				default:
					return $.trim(value).length > 0;
				}
			}
		},

		url : {
			argc : 1,
			// msg: "올바른 주소 형식으로 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
								.test(value);
			}
		},

		date : {
			argc : 1,
			// msg: "올바른 날짜 형식으로 입력하세요.",
			check : function(value, element, param) {

				return Validator.optional(element) || isValidDate(value);
			}
		},

		number : {
			argc : 1,
			// msg: "올바른 숫자 형식으로 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/
								.test(value);
			}
		},

		digit : {
			argc : 1,
			// msg: "숫자만 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element) || /^\d+$/.test(value);
			}
		},

		equal : {
			argc : 1,
			// msg: "{0}와 같은 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.findByName(param).val() == value;
			}
		},

		min : {
			argc : 1,
			// msg: "{0} 이상의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element) || value >= param;
			}
		},

		max : {
			argc : 1,
			// msg: "{0} 이하의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element) || value <= param;
			}
		},

		range : {
			argc : 2,
			// msg: "{0} ~ {1} 사이의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| (value >= param[0] && value <= param[1]);
			}
		},

		minlength : {
			argc : 1,
			// msg: "{0}자 이상의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| Validator.getLength($.trim(value), element) >= param;
			}
		},

		maxlength : {
			argc : 1,
			// msg: "{0}자 이하의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| Validator.getLength($.trim(value), element) <= param;
			}
		},

		rangelength : {
			argc : 2,
			// msg: "{0}자 ~ {1}자 사이의 값을 입력하세요.",
			check : function(value, element, param) {
				var length = Validator.getLength($.trim(value), element);
				return Validator.optional(element)
						|| (length >= param[0] && length <= param[1]);
			}
		},
		
		idrangelength : {
            argc : 2,
            // msg: "{0}자 ~ {1}자 사이의 값을 입력하세요.",
            check : function(value, element, param) {
                var length = Validator.getLength($.trim(value), element);
                return Validator.optional(element)
                        || (length >= param[0] && length <= param[1]);
            }
        },

		minbytes : {
			argc : 1,
			// msg: "{0}바이트 이상의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| Validator.getByteLength($.trim(value), element) >= param;
			}
		},

		maxbytes : {
			argc : 1,
			// msg: "{0}바이트 이하의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| Validator.getByteLength($.trim(value), element) <= param;
			}
		},

		maxbytesc : {
			argc : 1,
			// msg: "{0}바이트 이하의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| Validator.getBytesLength(value, element) <= param;
			}
		},

		maxbytesnotrim : {
			argc : 1,
			// msg: "{0}바이트 이하의 값을 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| Validator.getByteLength(value, element) <= param;
			}
		},

		rangebytes : {
			argc : 2,
			// msg: "{0}바이트 ~ {1}바이트 사이의 값을 입력하세요.",
			check : function(value, element, param) {
				var length = Validator.getByteLength($.trim(value), element);
				return Validator.optional(element)
						|| (length >= param[0] && length <= param[1]);
			}
		},

		xss : { // added (11/06/28)
			argc : 1,
			check : function(value, element, param) {
				if (!Validator.checkable(element)) {
					var val = $(element).val();
					var re = /\<|\>|alert *\(+|(\<|\<\/)script+|javascript+|document\.+|Document\.+|\.cookie+|\.Cookie+|\. Cookie+|xss\:+|\:expression|style\=+|background(\:|\.)+|\.\/+|\.\.\/+/g;

					if (val.match(re)) {
						return false;
					} else {
						return true;
					}
				}
			}
		},

		xss2 : {
			argc : 1,
			check : function(value, element, param) {
				if (!Validator.checkable(element)) {
					var val = $(element).val();
					var re = /\"|\'+/g;

					if (val.match(re)) {
						return false;
					} else {
						return true;
					}
				}
			}
		},

		xssquotation : { // 싱글, 더블 쿼테이션 제약조건 추가 xss+쿼테이션 조건
			argc : 1,
			check : function(value, element, param) {
				if (!Validator.checkable(element)) {
					var val = $(element).val();
					var re = /\<|\>|\"|\'|alert *\(+|(\<|\<\/)script+|javascript+|document\.+|Document\.+|\.cookie+|\.Cookie+|\. Cookie+|xss\:+|\:expression|style\=+|background(\:|\.)+|\.\/+|\.\.\/+/g;
					if (val.match(re)) {
						return false;
					} else {
						return true;
					}
				}
			}
		},

		onlyquotation : { // 싱글, 더블 쿼테이션 제약조건 추가 쿼테이션 조건만
			argc : 1,
			check : function(value, element, param) {
				if (!Validator.checkable(element)) {
					var val = $(element).val();
					var re = /\"|\'/g;

					if (val.match(re)) {
						return false;
					} else {
						return true;
					}
				}
			}
		},

		email : {
			argc : 1,
			// msg: "올바른 이메일 형식으로 입력하세요.",
			check : function(value, element, param) {
				return Validator.optional(element)
						|| /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d)|(([a-z]|\d)([a-z]|\d|-|\.|_|~)*([a-z]|\d)))\.)+(([a-z])|(([a-z])([a-z]|\d|-|\.|_|~)*([a-z])))\.?$/i
								.test(value);
			}
		},
		
		space : {
			argc :1,
			check : function(value, element, param) {
				if (!Validator.checkable(element)) {
					var val = $(element).val();
					var re =val.replace(/ /gi,'');
					if(val.length != re.length){
						return false;
					}else{
						return true;
					}
				}
			}
		},
		memberBlank : {
            argc :1,
            check : function(value, element, param) {
                return true;
            }
        },
		
		namerule : {
			argc:1,
			check : function(value, element, param) {
				var val = $(element).val();
				var loc = $('html').attr('lang');

				var re = '';
				if(loc == 'ko'){
					re = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$/;
				} else if(loc == 'en'){
					re = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*|\s]+$/;
				} else if(loc == 'zh'){
					re = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|\u4e00-\u9fff+|0-9|\*|\s]+$/;
				}

				if (val.match(re)) {
					return true;
				} else {
					return false;
				}
			}
		},

		phone : {
			argc:1,
			check : function(value, element, param) {
				var val = $(element).val();
				var loc = $('html').attr('lang');

//				var reg = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
				/* 현재는 국문만 사용하므로 한국 휴대폰번호 유형만 검증으로 변경. */
				/*
				var regExp;
				if(loc == 'ko'){
					regExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
				} else {
					regExp = /^[0-9](?:[- ]?[0-9]){9,12}$/;
				}
				*/
				//var regExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
				var regExp = /[01](0|1|6|7|8|9)(\d{4}|\d{3})\d{4}$/g;
				if(regExp.test(val)){
					return true;
				}else{
					return false;
				}
			}
		},
		tel : {
			argc:1,
			check : function(value, element, param) {
				var val = $(element).val();
				if(val.length > 0){
					var regExp = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]|70))(\d{3,4})(\d{4})$/;
					if(regExp.test(val)){
						return true;
					}else{
						return false;
					}
				}else{
					return true;
				}
			}
		},
		nohypen : {
			argc:1,
			check : function(value, element, param) {
				var val = $(element).val();
					var regExp = /\-/;
					if(regExp.test(val)){
						
						return false;
					}else{
						return true;
					}
			}
		},
		alphanumeric : {
			argc:1,
			check : function(value, element, param) {
				var val = $(element).val();
					var regExp =  /[^a-zA-Z0-9 ]/;
					if(regExp.test(val)){
						
						return false;
					}else{
						return true;
					}
			}
		},
		loweralphanumeric : {
			argc:1,
			check : function(value, element, param) {
				var val = $(element).val();
					var regExp =  /[^a-z0-9 ]/;
					if(regExp.test(val)){
						
						return false;
					}else{
						return true;
					}
			}
		},
		birthyear : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {
				// check if dependency is met
				if (!Validator.depend(param, element)) {
					return false;
				}

				return $.trim(value).length > 0;
			}
		},
		birthmonth : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {
				// check if dependency is met
				if (!Validator.depend(param, element)) {
					return false;
				}

				return $.trim(value).length > 0;
			}
		},
		birthdate : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {
				// check if dependency is met
				if (!Validator.depend(param, element)) {
					return false;
				}

				return $.trim(value).length > 0;
			}
		},
		gender : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {
				// check if dependency is met				
				if (!Validator.depend(param, element)) {
					return false;
				}

				return $.trim(value).length > 0;
			}
		},
		checkval : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {				
				return $(element).is(":checked");
			}
		},
		mbrid : {
			argc : 1,
			// msg: "필수항목입니다.",
			check : function(value, element, param) {				
				
				var val = $(element).val();
				var regExp =  /[^a-z0-9 ]/;
				if(regExp.test(val)){					
					return false;
				}else{
					return true;
				}
			}
		}
	},

	locales : {
		en : {
			messages : {
				required : "Please enter the 'T'",
				email : "Invalid email address format.",
				url : "Please input by the right url format ",
				date : "Please input by the right date format ",
				number : "Please input by the right number format ",
				digit : "Please input only a number ",
				equal : "Please input the same value as {0} ",
				min : "Please input the value beyond {0} ",
				max : "Please input the value following {0} ",
				range : "Please input the value between {0} and {1} ",
				minlength : "Please input the characters beyond {0} ",
				maxlength : "Please input the characters following {0} ",
				idrangelength : "An ID must be at least six characters and must use only English letters, only numbers, only English letters+numbers. ",
				rangelength : "Invalid ID. ID must be between {0} and {1} characters ",
				minbytes : "Please input min length {0} ",
				maxbytes : "Please input max length {0} ",
				maxbytesc : "Please input  (max {0} bytes)",
				maxbytesnotrim : "Please input max length {0} ",
				rangebytes : "Please input length between {0} and {1} ",
				xss : "A special character can not be input ",
				xss2 : "A special character can not be input ",
				xssquotation : "A special character can not be input ",
				onlyquotation : "A special character can not be input ",
				space : "Enter without blank.",
				namerule : "Enter Korean, English, and numbers only.",
				phone : "Invalid phone number format.",
				tel : "Invalid phone number",
				nohypen : "Please enter without '-'",
				alphanumeric : "Please enter numbers or English Alphabet only.",
				loweralphanumeric : "Please enter numbers or lower case English Alphabet only.",
				birthyear : "태어난 년도 4자리를 정확하게 입력하세요.",
				birthmonth : "태어난 달을 선택해 주세요.",
				birthdate : "태어난 일(날짜) 2자리를 정확하게 입력하세요.",
				gender : "성별을 선택해 주세요.",
				checkval : "'T'을(를) 체크해 주세요.",				
				mbrid : "6~15자 영문 소문자, 숫자만 사용 가능하며 특수문자는 사용할 수 없습니다."
			}
		},

		ko : {
			messages : {
				required : "'T'을(를) 입력해 주세요.",
				email : "잘못된 이메일 형식입니다.",
				url : "올바른 주소 형식으로 입력하세요.",
				date: " 올바른 날짜 형식으로 입력하세요." ,
//				date : "8자로 입력해 주세요.",
				number : "올바른 숫자 형식으로 입력하세요.",
				digit : "숫자만 입력하세요. ",
				equal : "{0}와 같은 값을 입력하세요. ",
				min : "{0} 이상의 값을 입력하세요. ",
				max : "{0} 이하의 값을 입력하세요. ",
				range : "{0} ~ {1} 사이의 값을 입력하세요. ",
				minlength : "{0}자 이상의 값을 입력하세요. ",
				maxlength : "{0}자 이하의 값을 입력하세요. ",
				idrangelength : "아이디는 {0}자 이상의 '영문' 또는 '숫자' 또는 '영문+숫자'로 구성되어야 합니다. ",
				rangelength : "{0}자 이상 {1}자 이하 입니다.",
				minbytes : "{0}글자 이상을 입력하세요. ",
				maxbytes : " {0}글자 이하를 입력하세요. ",
				maxbytesc : "{0}바이트 이하를 입력하세요. ",
				maxbytesnotrim : "{0}글자 이하를 입력하세요. ",
				rangebytes : "{0} ~ {1} 사이의 글자를 입력하세요. ",
				xss : " 특수문자는 입력할 수 없습니다. ",
				xss2 : " 특수문자는 입력할 수 없습니다. ",
				xssquotation : " 특수문자는 입력할 수 없습니다. ",
				onlyquotation : " 특수문자는 입력할 수 없습니다. ",
				space : "공백 없이 입력하세요.",
				namerule : "한글, 영어, 숫자만 입력할 수 있습니다.",
				phone : "휴대전화번호를 확인해 주세요.",
				tel : "전화번호를 확인해 주세요.",
				nohypen : "'-' 없이 입력해주세요",
				alphanumeric : "숫자 및 영문만 입력하세요.",
				loweralphanumeric : "숫자 및 영문(소문자)만 입력하세요.",
				birthyear : "태어난 년도 4자리를 정확하게 입력하세요.",
				birthmonth : "태어난 달을 선택해 주세요.",
				birthdate : "태어난 일(날짜) 2자리를 정확하게 입력하세요.",
				gender : "성별을 선택해 주세요.",
				checkval : "'T'을(를) 체크해 주세요.",				
				mbrid : "6~15자 영문 소문자, 숫자만 사용 가능하며 특수문자는 사용할 수 없습니다."
			}
		}
	}
};

/**
 * 지정된 곳 외의 Enter 이벤트 방지. 사용법 document.onkeydown = blockEnterKey;
 * 
 * @return
 */
function blockEnterKey() {
	try {
		if (!e)
			var e = window.event;

		if (document.all)
			var key = e.keyCode;
		else
			var key = e.which;

		if (key == 13) {
			// var tag = e.srcElement ? e.srcElement.tagName : e.target.nodeName
			// ;
			// var tagId = e.srcElement ? e.srcElement.id : "" ;
			return false;
		}
	} catch (e) {
		return true;
	}
}

function handlerNumeral(e) {

	var keyCode = (window.event) ? window.event.keyCode : e.which;

	if (48 <= keyCode && keyCode <= 57 || 96 <= keyCode && keyCode <= 105
			|| keyCode == 8 || keyCode == 9 || keyCode == 17 || keyCode == 18
			|| keyCode == 37 || keyCode == 39 || keyCode == 46
			|| keyCode == 110 || keyCode == 190)
		return;
	else {
		if (window.event)
			window.event.returnValue = false;
		else {
			e.preventDefault();
			e.stopPropagation();
		}
	}
}

/**
 * @param password
 * @param attr : 있으면 해당 attr의 .error-msg에 출력, 없으면 alertLayer
 * @param rtnMsg : true 해당 msg 리턴, 없으면 리턴 true, false
 * 
 * 비밀번호 유효성 검사
 * 1. 영문 대/소문자, 숫자, 특수기호 중 2가지 이상 조합
 * 2. 8자 이상, 12자 이하
 * 3. 동일문자 3회이상 반복 불가
 * 4. 연속된 숫자/문자 3자 이상 사용불가
 */
function validatePassword(password, attr, rtnMsg) {
	if(attr == undefined) attr = null;
//	if(layer == undefined || layer == null) layer = false;
	if(rtnMsg == undefined) rtnMsg = null;
	
	// 영문 대/소문자, 숫자, 특수기호 중 2가지 이상 조합
	var valid_count = 0;
	var alphabet_count = 0;
	var number_count = 0;

	if (password.match(/[a-zA-Z]/) != null) {
		valid_count++;
		alphabet_count++;
	}
	if (password.match(/[0-9]/) != null) {
		valid_count++;
		number_count++;
	}
	
    var specialChars="!,@#$%^&*"; // 보안허용 특수문자 9가지
    var inputTextData = password; // 입력받은 데이터
    var charCnt = 0; // 입력받은 데이터 카운트
    var specialCnt = 0; // 비교하는 특수문자 카운트
    var matchChar ; 
    var textArr = [];
    var result = [];
    var specialFlag = false;
    var msg = "";
    
	for (i = 0; i < inputTextData.length; i++) {
		var re = /[a-zA-Z0-9]/;

		matchChar = inputTextData.charAt(i);

		if(matchChar.match(/[a-z|A-Z|0-9]/) != null) {
			// 입력받은 문자가 특수문자가 아니면 패스
		}
		else {
			specialFlag = true;
			// 입력받은 문자가 특수문자, 규정에 있는 9가지 특수문자인지 체크
			specialCnt++;

			// 9개 특수문자만큼 for문 돌면서 비교
			for (j = 0; j < specialChars.length; j++) {
				if (matchChar == specialChars.charAt(j)){
					charCnt++;
				}
				else {
					// 지정된 특수문자가 아님
					textArr.push(matchChar);
				}
			}

			// 특수문자 중복제거
			if(textArr.length > 0) {
				$.each(textArr, function(index, element) {
					if ($.inArray(element, result) == -1) {  // result 에서 값을 찾는다.  //값이 없을경우(-1)
						result.push(element);                // result 배열에 값을 넣는다.
					}
				});
			}
		}
	}

	if(specialFlag == true) {
		if(charCnt >= specialCnt) {
			// 지정된 특수문자가 입력되었으므로 validation 카운트 증가
			valid_count++;
		}
	}

	var lastDataArr = []; //화면에 허용된 특수문자 텍스트 만 표시하기 위한 배열
	for(k = 0; k < result.length; k++) {
		if(specialChars.indexOf(result[k]) == -1){
			lastDataArr.push(result[k]);
		}
	}

	if(charCnt < specialCnt) {
		msg = MESSAGES['member.js.join.lbl.error.msg7'];
		if(rtnMsg == null) {
			if (attr != null) {
				if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
					attr.closest("li").find(".error-msg").text(msg);
					errorMsgShow(attr.closest("li").find(".error-msg"));
				}
				else if(attr.next().attr("class") == "error-msg") {
					attr.next().text(msg);
					errorMsgShow(attr.next());
				}
				
				setTimeoutFocus(attr.attr("id"));
			}
			else {
				alertLayer(msg);
			}
			return false;
		}
		else {
			return rtnMsg;
		}
	}

	// 영문만 입력했을 경우
	if(alphabet_count > 0 && number_count == 0 && specialCnt == 0) {
		msg = MESSAGES['member.js.join.lbl.error.msg7'];
		if(rtnMsg == null) {
			if (attr != null) {
				if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
					attr.closest("li").find(".error-msg").text(msg);
					errorMsgShow(attr.closest("li").find(".error-msg"));
				}
				else if(attr.next().attr("class") == "error-msg") {
					attr.next().text(msg);
					errorMsgShow(attr.next());
				}

				setTimeoutFocus(attr.attr("id"));
			}
			else {
				alertLayer(msg);
			}
			return false;
		}
		else {
			return rtnMsg;
		}
	}
	// 숫자만 입력했을 경우
	if(number_count > 0 && alphabet_count == 0 && specialCnt == 0) {
		msg = MESSAGES['member.js.join.lbl.error.msg7'];
		if (attr != null) {
			if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
				attr.closest("li").find(".error-msg").text(msg);
				errorMsgShow(attr.closest("li").find(".error-msg"));
			}
			else if(attr.next().attr("class") == "error-msg") {
				attr.next().text(msg);
				errorMsgShow(attr.next());
			}

			setTimeoutFocus(attr.attr("id"));
		}
		else {
			alertLayer(msg);
		}
		return false;
	}
	// 특수문자만 입력했을 경우
	if(specialCnt > 0 && alphabet_count == 0 && number_count == 0) {
		msg = MESSAGES['member.js.join.lbl.error.msg7'];
		if(rtnMsg == null) {
			if (attr != null) {
				if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
					attr.closest("li").find(".error-msg").text(msg);
					errorMsgShow(attr.closest("li").find(".error-msg"));
				}
				else if(attr.next().attr("class") == "error-msg") {
					attr.next().text(msg);
					errorMsgShow(attr.next());
				}

				setTimeoutFocus(attr.attr("id"));
			}
			else {
				alertLayer(msg);
			}
			return false;
		}
		else {
			return rtnMsg;
		}
	}
	// 8~12자리수가 아닌 경우
	if(password.length < 8 || password.length > 12) {
		msg = MESSAGES['member.js.join.lbl.error.msg7'];
		if(rtnMsg == null) {
			if (attr != null) {
				if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
					attr.closest("li").find(".error-msg").text(msg);
					errorMsgShow(attr.closest("li").find(".error-msg"));
				}
				else if(attr.next().attr("class") == "error-msg") {
					attr.next().text(msg);
					errorMsgShow(attr.next());
				}

				setTimeoutFocus(attr.attr("id"));
			}
			else {
				alertLayer(msg);
			}
			return false;
		}
		else {
			return rtnMsg;
		}
	}
	// 2가지 이상의 조합이 아닌 경우
	if (valid_count < 2) {
		msg = MESSAGES['member.js.join.lbl.error.msg7'];
		if(rtnMsg == null) {
			if (attr != null) {
				if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
					attr.closest("li").find(".error-msg").text(msg);
					errorMsgShow(attr.closest("li").find(".error-msg"));
				}
				else if(attr.next().attr("class") == "error-msg") {
					attr.next().text(msg);
					errorMsgShow(attr.next());
				}

				setTimeoutFocus(attr.attr("id"));
			}
			else {
				alertLayer(msg);
			}
			return false;
		}
		else {
			return rtnMsg;
		}
	}
	
	var strights = [ '012345678901', '987654321098',
        'abcdefghijklmnopqrstuvwxyzab', 'zyxwvutsrqponmlkjihgfedcbazy', 'ABCDEFGHIJKLMNOPQRSTUVWXYZAB', 'ZYXWVUTSRQPONMLKJIHGFEDCBAZY' ];

	var getPattern = function(str, casesensitive) {
		// 정규식 생성전에 예약어를 escape 시킨다.
		var reserves = [ '\\', '^', '$', '.', '[', ']', '{', '}', '*', '+', '?', '(', ')', '|' ];

		$.each(reserves, function(index, reserve) {
			var pattern = new RegExp('\\' + reserve, 'g');
			if (pattern.test(str)) {
				str = str.replace(pattern, '\\' + reserve);
			}
		});
		var pattern = null;
		if (casesensitive == false) {
			pattern = new RegExp(str, 'i');
		} else {
			pattern = new RegExp(str);
		}
		return pattern;
	};
	
	for (var i = 0; i < password.length; i++) {
		if (password.charAt(i + 1) != '' && password.charAt(i + 2) != '') {
			// 동일문자 3회이상 반복 불가
			if (password.charCodeAt(i) == password.charCodeAt(i + 1)
					&& password.charCodeAt(i + 1) == password.charCodeAt(i + 2)) {
				msg = MESSAGES['member.js.join.lbl.error.msg8'];
				if(rtnMsg == null) {
					if (attr != null) {
						if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
							attr.closest("li").find(".error-msg").text(msg);
							errorMsgShow(attr.closest("li").find(".error-msg"));
						}
						else if(attr.next().attr("class") == "error-msg") {
							attr.next().text(msg);
							errorMsgShow(attr.next());
						}

						setTimeoutFocus(attr.attr("id"));
					}
					else {
						alertLayer(msg);
					}
					return false;
				}
				else {
					return rtnMsg;
				}
			}
			var str = password.charAt(i) + '' + password.charAt(i + 1) + '' + password.charAt(i + 2);

			var pattern = getPattern(str, true);

			// 연속된 숫자/문자 3자 이상 사용불가
			for (var j = 0; j < strights.length; j++) {
				if (pattern.exec(strights[j]) != null) {
					msg = MESSAGES['member.js.join.lbl.error.msg9'];
					if(rtnMsg == null) {
						if (attr != null) {
							if(attr.closest("li").find(".error-msg") != undefined && attr.closest("li").find(".error-msg").length > 0) {
								attr.closest("li").find(".error-msg").text(msg);
								errorMsgShow(attr.closest("li").find(".error-msg"));
							}
							else if(attr.next().attr("class") == "error-msg") {
								attr.next().text(msg);
								errorMsgShow(attr.next());
							}

							setTimeoutFocus(attr.attr("id"));
						}
						else {
							alertLayer(msg);
						}
						return false;
					}
					else {
						return rtnMsg;
					}
				}
			}
		}
	}
	
	return true;
}


function isValidDate(param) {
	try {
		param = param.replace(/-/g, '');

		// 자리수가 맞지않을때
		if (isNaN(param) || param.length != 8) {
			return false;
		}

		var year = Number(param.substring(0, 4));
		var month = Number(param.substring(4, 6));
		var day = Number(param.substring(6, 8));

		var dd = day / 0;

		if (month < 1 || month > 12) {
			return false;
		}

		var maxDaysInMonth = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
		var maxDay = maxDaysInMonth[month - 1];

		// 윤년 체크
		if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
			maxDay = 29;
		}

		if (day <= 0 || day > maxDay) {
			return false;
		}
		return true;

	} catch (err) {
		return false;
	}
}
