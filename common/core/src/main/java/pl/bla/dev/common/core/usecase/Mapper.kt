package pl.bla.dev.common.core.usecase

interface Mapper<FROM, TO> {
  operator fun invoke(params: FROM): TO
}