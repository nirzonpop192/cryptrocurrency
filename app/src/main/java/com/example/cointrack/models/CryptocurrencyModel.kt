package com.example.cointrack.models
import com.google.gson.annotations.SerializedName


data class CryptoModel(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("status")
    val status: Status
) {
    data class Data(
        @SerializedName("circulating_supply")
        val circulatingSupply: Double, // 122373866.2178
        @SerializedName("cmc_rank")
        val cmcRank: Int, // 1
        @SerializedName("date_added")
        val dateAdded: String, // 2013-04-28T00:00:00.000Z
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("last_updated")
        val lastUpdated: String, // 2022-12-19T05:41:00.000Z
        @SerializedName("max_supply")
        val maxSupply: Long, // 100000000000
        @SerializedName("name")
        val name: String, // Bitcoin
        @SerializedName("num_market_pairs")
        val numMarketPairs: Int, // 9900
        @SerializedName("platform")
        val platform: Platform,
        @SerializedName("quote")
        val quote: Quote,
        @SerializedName("self_reported_circulating_supply")
        val selfReportedCirculatingSupply: Double, // 279521613.54528904
        @SerializedName("self_reported_market_cap")
        val selfReportedMarketCap: Double, // 4185395635.861447
        @SerializedName("slug")
        val slug: String, // bitcoin
        @SerializedName("symbol")
        val symbol: String, // BTC
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("total_supply")
        val totalSupply: Double, // 122373866.2178
        @SerializedName("tvl_ratio")
        val tvlRatio: Double // 1.21164098
    ) {
        data class Platform(
            @SerializedName("id")
            val id: Int, // 1027
            @SerializedName("name")
            val name: String, // Ethereum
            @SerializedName("slug")
            val slug: String, // ethereum
            @SerializedName("symbol")
            val symbol: String, // ETH
            @SerializedName("token_address")
            val tokenAddress: String // 0xdac17f958d2ee523a2206206994597c13d831ec7
        )

        data class Quote(
            @SerializedName("USD")
            val uSD: USD
        ) {
            data class USD(
                @SerializedName("fully_diluted_market_cap")
                val fullyDilutedMarketCap: Double, // 351071013867.07
                @SerializedName("last_updated")
                val lastUpdated: String, // 2022-12-19T05:41:00.000Z
                @SerializedName("market_cap")
                val marketCap: Double, // 321609151101.016
                @SerializedName("market_cap_dominance")
                val marketCapDominance: Double, // 39.8686
                @SerializedName("percent_change_1h")
                val percentChange1h: Double, // -0.05953227
                @SerializedName("percent_change_24h")
                val percentChange24h: Double, // -0.21641446
                @SerializedName("percent_change_30d")
                val percentChange30d: Double, // 0.74473963
                @SerializedName("percent_change_60d")
                val percentChange60d: Double, // -12.63585446
                @SerializedName("percent_change_7d")
                val percentChange7d: Double, // -1.33950707
                @SerializedName("percent_change_90d")
                val percentChange90d: Double, // -13.75081385
                @SerializedName("price")
                val price: Double, // 16717.6673270035
                @SerializedName("tvl")
                val tvl: Double, // 3340394884.4212003
                @SerializedName("volume_24h")
                val volume24h: Double, // 12320653309.91013
                @SerializedName("volume_change_24h")
                val volumeChange24h: Double // 2.8232
            )
        }
    }

    data class Status(
        @SerializedName("credit_count")
        val creditCount: Int, // 1
        @SerializedName("elapsed")
        val elapsed: Int, // 20
        @SerializedName("error_code")
        val errorCode: Int, // 0
        @SerializedName("error_message")
        val errorMessage: Any, // null
        @SerializedName("notice")
        val notice: Any, // null
        @SerializedName("timestamp")
        val timestamp: String, // 2022-12-19T05:43:30.478Z
        @SerializedName("total_count")
        val totalCount: Int // 8977
    )
}