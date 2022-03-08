/**
 * Validação de formulário
 * @author Bruna Garcia
 */
 
 function validar() {
	let nome = formContato.nome.value;
	let telefone = formContato.telefone.value;
	let email = formContato.email.value;
	
	if (nome === '') {
		alert('Preencha o campo nome');
		formContato.nome.focus();
		return;
	}
	
	if (telefone === '') {
		alert('Preencha o campo telefone');
		formContato.telefone.focus();
		return;
	}
	
	document.forms['formContato'].submit();
}
 