package com.android.myapplication.model

data class Response(
	val result: Result? = null,
	val help: String? = null,
	val success: Boolean = false
)

data class Result(
	val total: Int? = null,
	val records: List<RecordsItem>? = null,
	val links: Links? = null,
	val limit: Int? = null,
	val resourceId: String? = null,
	val fields: List<FieldsItem?>? = null
)

data class Links(
	val next: String? = null,
	val start: String? = null
)

data class RecordsItem(
	val volumeOfMobileData: String? = null,
	val id: Int? = null,
	val quarter: String? = null
)

data class FieldsItem(
	val id: String? = null,
	val type: String? = null
)

