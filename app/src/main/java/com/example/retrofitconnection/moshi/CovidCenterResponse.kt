package com.example.retrofitconnection.moshi


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CovidCenterResponse(
    @field:Json(name = "CORSVST")
    val cORSVST: List<CORSVST>
)

data class CORSVST(
    @field:Json(name = "head")
    val head: Head?,
    @field:Json(name = "row")
    val row: List<Row?>?
)

data class Head(
    @field:Json(name = "api_version")
    val apiVersion: String?,
    @field:Json(name = "list_total_count")
    val listTotalCount: Int?,
    @field:Json(name = "RESULT")
    val rESULT: RESULT?
)

data class RESULT(
    @field:Json(name = "CODE")
    val cODE: String?,
    @field:Json(name = "MESSAGE")
    val mESSAGE: String?
)

data class Row(
    @field:Json(name = "ADDR")
    val aDDR: String?,
    @field:Json(name = "CENTER_NM")
    val cENTERNM: String?,
    @field:Json(name = "CENTER_TYPE")
    val cENTERTYPE: String?,
    @field:Json(name = "OPR_INST_NM")
    val oPRINSTNM: String?,
    @field:Json(name = "REFINE_WGS84_LAT")
    val rEFINEWGS84LAT: Double?,
    @field:Json(name = "REFINE_WGS84_LOGT")
    val rEFINEWGS84LOGT: Double?,
    @field:Json(name = "SI_DESC")
    val sIDESC: String?,
    @field:Json(name = "ZIPNO")
    val zIPNO: Int?
)