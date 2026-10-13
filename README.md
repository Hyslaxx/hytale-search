# Hytale Better Search

**Smarter item search for Hytale's Creative Mode.**

Hytale Better Search is an experimental mod project aimed at improving the Creative Mode item search experience through typo-tolerant, multilingual, and community-aware search.

The goal is simple: **find the item you want, even when you don't type its exact name.**

## 💡 The Idea

Traditional item searches can be frustrating when you make a typo, use a different language, or search for an item using a name that differs from the game's translation.

Hytale Better Search aims to solve these problems.

### ✨ Planned Features

- **Typo-tolerant search:** Search for `espadd` and find `espada`.
- **Cross-language search:** Search for `sword` in a Portuguese game and still find `espada`.
- **Community terminology:** Recognize familiar names, aliases, and commonly used terms instead of relying exclusively on literal translations.
- **Language-aware results:** Respect the player's selected game language while accepting relevant terms from other languages.
- **Better search relevance:** Prioritize the most likely matches instead of requiring an exact name.

### 🔎 Examples

| Search Query | Expected Result |
|---|---|
| `espadd` | `espada` |
| `sword` | `espada` |
| `picareet` | `picareta` |
| `bedrock` | The corresponding block, regardless of the game's language |

*These are intended behaviors, not verified in-game results.*

## 🚧 Project Status

**Early development — research and prototyping.**

The project is currently exploring search algorithms, multilingual item matching, and the possibilities offered by Hytale's modding APIs.

- [ ] Research Hytale's modding capabilities
- [ ] Build a typo-tolerant search prototype
- [ ] Implement multilingual aliases
- [ ] Investigate Creative Mode UI integration
- [ ] Test in Hytale
- [ ] Release an initial version

## 🛠️ Development

The initial focus is on developing and testing the search logic independently before attempting integration with Hytale's Creative Mode interface.

The final implementation will depend on the available modding APIs and technical limitations.

## 🙏 Inspiration and Credits

This project is inspired by **Better Search by Rivalzin**, a Minecraft mod that improves item searching.

The goal is to explore a similar concept for Hytale, with an emphasis on multilingual search and familiar community terminology.

Original project: [Rivalzin's Better Search](https://github.com/rivalzin/bettersearch)

Any third-party code, assets, and dependencies will be credited and used according to their respective licenses.

## ⚠️ Disclaimer

This is an independent community project and is not affiliated with or endorsed by Hypixel Studios.

Hytale is a trademark of its respective owner.

## 🤝 Contributing

Suggestions, feedback, and ideas for improving item search are welcome.

Have an idea for a search term, typo, translation, or alias that should work? Feel free to open an issue.

---

**The goal:** Spend less time searching for items and more time building.
