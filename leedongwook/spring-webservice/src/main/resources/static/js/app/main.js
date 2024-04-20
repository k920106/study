var main = {

	init : function () {

		var _this = this;

		/*게시글 등록*/
		$('#btn-save').on('click', function () {
			_this.save();
		});

		/*상세보기*/
		$('#listall .abutton').on('click', function () {

			var text = $(this).closest("tr").find("td:first").text();

			$('#hide-text').val(text);

			_this.one();
			_this.loadingComment();
		});

		/*댓글 등록*/
		$('#save-comment').on('click', function(){
			_this.saveComment();
			_this.loadingComment();
		});

		/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

		/*회원 리스트 - 수정창 클릭*/
    $('#listall .userModify').on('click', function() {

			var text = $(this).closest("tr").find("td:first").text();

			$('#hide-text').val(text);

		});

    $('#editSave').on('click', function() {
      console.log('click')
      _this.userModifeid();
    });

		$('.deleteModify').on('click', function() {

			var text = $(this).closest("tr").find("td:first").text();

			$('#hide-text').val(text);

			_this.userDeleted();
		});

	}
	,

	/*게시글 등록*/
	save : function() {
		var data = {
			title: $('#title').val(),
			author: $('#author').val(),
			content: $('#content').val()
		};

		$.ajax({
			type: 'POST',
			url: '/posts',
			dataType: 'json',
			contentType:'application/json; charset=utf-8',
			data: JSON.stringify(data)
		}).done(function() {
		}).fail(function (error) {
		});
	}
	,


	/*상세 보기*/
	one : function() {

		var data = {
			id : $('#hide-text').val()
		};

		$.ajax({
			type: 'POST',
			url: '/info',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data),
		}).done(function(data){

			$('#info-num').val(data.id);

			$('#info-title').val(data.title);

			$('#info-author').val(data.author);

		}).fail(function () {
			alert("에러")
		});
	}
	,


	/*댓글 등록*/
	saveComment : function() {

		console.log($('#info-num').val());
		console.log($('#info-comment').val());

		var data = {
			listNum : $('#info-num').val(),
			comment : $('#info-comment').val()
		};

		$.ajax({
			type: 'POST',
			url: '/saveComment',
			dataType: 'json',
			contentType:'application/json; charset=utf-8',
			data: JSON.stringify(data)
		}).done(function() {
		}).fail(function (error) {
		});

	}
	,


	/*댓글 조회*/
	loadingComment : function() {

		$.ajax({
			type: 'GET',
			url: '/infocomment/' + $('#hide-text').val(),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8'
		}).done(function(data){

			$("#comment-list").empty();

			$.each(data, function (index, item) {
				$("#comment-list").append(item.comment +'<br>');
			});

		}).fail(function () {
			alert("에러")
		});
	},

	/*회원리스트 - 수정창 클릭*/
	userModifeid : function() {

    var data = {
      title: $('#editTitle').val(),
      content: $('#editContent').val()
    };


		$.ajax({
			type: 'POST',
			url: '/list-edit/' + $("#hide-text").val(),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
		}).done(function(){
		}).fail(function(err) {
		});

	},



	/*회원리스트 - 수정창 클릭*/
	userDeleted : function() {

	$.ajax({
		type: 'GET',
		url: '/list-delete/' + $("#hide-text").val(),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8'
	}).done(function(){
	}).fail(function(err) {
	});

}




};

main.init();