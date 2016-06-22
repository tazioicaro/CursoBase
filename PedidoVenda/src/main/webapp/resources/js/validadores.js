PrimeFaces.locales['pt_BR'] = {
	messages : {
		'org.hibernate.validator.constraints.NotBlank.message' : '{0} não pode estar em branco',
		'org.hibernate.validator.constraints.NotEmpty.message' : '{0} não pode estar vazio',
		'javax.validation.constraints.NotNull.message' : '{0} não pode ser nulo',
		'javax.faces.validator.LengthValidator.MAXIMUM' : '{1} deve ter no máximo {0} caracteres',
		'javax.faces.component.UIInput.REQUIRED' : '{0} é obrigatório',	
		'com.algaworks.validator.constraints.SKU.message' : '{0} dever ser informado no formato correto (exemplo: XX9999)',
		'javax.faces.validator.RegexValidator.MATCH_EXCEPTION' :'{0} expressão regular errada', 
		'javax.faces.validator.RegexValidator.NOT_MATCHED' : '{0} padrão de escrita não seguido',
		'javax.faces.validator.RegexValidator.PATTERN_NOT_SET' : '{0} padrão da REGEX não pode ser definido como vazio'
			
	
	}
};

PrimeFaces.validator.NotNull = {

	MESSAGE_ID : "javax.validation.constraints.NotNull.message",

	validate : function(c, d) {
		var label = c.data('p-label');
		if (d === null || d === undefined) {
			var b = PrimeFaces.util.ValidationContext, a = c.data("p-notnull-msg"), e = (a) ? {
				summary : a,
				detail : a
			} : b.getMessage(this.MESSAGE_ID, label);
			throw e
		}
	}
};

PrimeFaces.converter['com.algaworks.Categoria'] = {
	
	convert : function(element, value) {
		if (value === null || value === '') {
			return null;
		}
		
		return parseInt(value);
	}
		
};

PrimeFaces.validator.NotBlank = {
	
	MESSAGE_ID : 'org.hibernate.validator.constraints.NotBlank.message',
		
	validate : function(element, value) {
		if (value === null || value === undefined || value.trim() === '') {
			var msg = element.data('msg-notblank');
			var label = element.data('p-label');
			var context = PrimeFaces.util.ValidationContext;
			var msgObj;
			
			if (!msg) {
				msgObj = context.getMessage(this.MESSAGE_ID, label);
			} else {
				var msgObj = {
					summary : msg,
					detail : msg
				}
			}
			
			throw msgObj;
		}
	}
		
};

PrimeFaces.validator.NotEmpty = {
		
		MESSAGE_ID : 'org.hibernate.validator.constraints.NotEmpty.message',
			
		validate : function(element, value) {
			if (value === null || value === undefined || value.trim() === '') {
				var msg = element.data('msg-notempty');
				var label = element.data('p-label');
				var context = PrimeFaces.util.ValidationContext;
				var msgObj;
				
				if (!msg) {
					msgObj = context.getMessage(this.MESSAGE_ID, label);
				} else {
					var msgObj = {
						summary : msg,
						detail : msg
					}
				}
				
				throw msgObj;
			}
		}
			
	};

PrimeFaces.validator.SKU = {
	
	pattern : /^([a-zA-Z]{2}\d{4,18})?$/,
		
	validate : function(element, value) {
		if (!this.pattern.test(value)) {
			var msg = element.data('msg-sku');
			
			if (!msg) {
				msg = 'SKU dever ser informado no formato correto (exemplo: XX9999).';
			}
			
			var msgObj = {
				summary : msg,
				detail : msg
			}
			
			throw msgObj;
		}
	}
		
};

PrimeFaces.validator.Highlighter.types.onemenu = {
	highlight : function(a) {
		a.parent().siblings(".ui-selectonemenu-trigger").addClass(
				"ui-state-error").parent().addClass(
				"ui-state-error");
		PrimeFaces.validator.Highlighter.highlightLabel(a.parent().parent().find('div input'))
	},
	unhighlight : function(a) {
		a.parent().siblings(".ui-selectonemenu-trigger")
				.removeClass("ui-state-error").parent()
				.removeClass("ui-state-error");
		PrimeFaces.validator.Highlighter.unhighlightLabel(a
				.parent().parent().find('div input'))
	}
};