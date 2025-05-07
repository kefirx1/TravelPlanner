package pl.bla.dev.common.usecase

interface Mapper<FROM, TO> {
  operator fun invoke(from: FROM): TO
}