package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.domain.{Article, ArticleId}
import java.time.LocalDateTime

object MockData {

  val articles = List(
    Article(
      id = ArticleId(1),
      slug = "article1",
      title = "3 Stocks to Watch Heading Into Earnings Season",
      description =
        "Silvergate Capital, Live Oak Bancshares, and Goosehead Insurance are growing fast, with unique business models.",
      body =
        "Earnings season is upon us once again, and while long-term investors shouldn't put too much weight on any company's results from any single quarter, these regular check-ins can certainly give investo… [+4866 chars]",
      createdAt = LocalDateTime.now,
      updatedAt = LocalDateTime.now
    ),
    Article(
      id = ArticleId(2),
      slug = "article2",
      title = "NFTs not annoying enough? Now they come with wallet-emptying malware",
      description =
        "Plus rifle-toting robot dogs, but makers insist they're really dumb\\nIn brief Whether or not non-fungible tokens are a flash in the pan or forever, malware operators have been keen to weaponise the technology.…<!--#include virtual='/data_centre/_whitepaper_tex…",
      body =
        "In brief Whether or not non-fungible tokens are a flash in the pan or forever, malware operators have been keen to weaponise the technology.\\r\\nAn investigation was triggered after a number of cryptowa… ",
      createdAt = LocalDateTime.now,
      updatedAt = LocalDateTime.now
    ),
    Article(
      id = ArticleId(3),
      slug = "article3",
      title = "More Than One-Fourth Of America’s 400 Richest Went To One Of These 12 Colleges",
      description =
        "Not surprisingly, \u200Bmost of the Ivy League universities are among the dozen institutions, as are a couple of high-ranking state schools.",
      body =
        "From left: Tyler Winklevoss, Larry Page, Robert F. Smith, Laurene Powell Jobs and Cameron Winklevoss. \\r\\nImeh Akpanudosen/Getty Images, David Paul Morris/Bloomberg, Tim Pannell for Forbes, Neilson Bar…",
      createdAt = LocalDateTime.now,
      updatedAt = LocalDateTime.now
    ),
    Article(
      id = ArticleId(4),
      slug = "article4",
      title = "3 Reasons to Invest in Crypto -- and 3 Reasons Not To",
      description =
        "Cryptocurrency is one of the most exciting developments in finance this century. But there are still many questions you as an investor need to answer before diving in.",
      body =
        "Whenever I meet someone who learns I work in finance, the first question I inevitably get is, \\\"What are your thoughts on crypto?\\\"\\r\\nIt's not a surprise. After all, the price of Bitcoin(CRYPTO:BTC) has",
      createdAt = LocalDateTime.now,
      updatedAt = LocalDateTime.now
    )
  )

}
